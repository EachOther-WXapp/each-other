package com.wutong.weixin.service.impl;

import com.wutong.weixin.dao.FileDao;
import com.wutong.weixin.entity.File;
import com.wutong.weixin.service.FileService;
import com.wutong.weixin.utils.application.RequestHolder;
import com.wutong.weixin.utils.date.CalendarUtil;
import com.wutong.weixin.utils.date.DateFormatUtil;
import com.wutong.weixin.utils.encrypt.Base64Util;
import com.wutong.weixin.utils.encrypt.HashUtil;
import com.wutong.weixin.utils.enums.FileSaveType;
import com.wutong.weixin.utils.enums.FileType;
import com.wutong.weixin.utils.exception.BusinessException;
import com.wutong.weixin.utils.exception.StatusCodeEnum;
import com.wutong.weixin.utils.file.FileConvertUtil;
import com.wutong.weixin.utils.file.FileUploadUtil;
import com.wutong.weixin.utils.http.MimeTypeUtil;
import com.wutong.weixin.utils.random.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Tom
 * Date：2017/11/1
 * Email：bibi8086@gmail.com
 */
@Service
public class FileServiceImpl implements FileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${file.root:/data/upload}")
    private String root;

    @Value("${file.fileDir:/files}")
    private String fileDir;

    @Value("${file.imageDir:/images}")
    private String imageDir;

    @Value("${file.fileSuffix:jpeg,jpg,gif,png,ico,bmp,doc,docx,xls,xlsx,ppt,pptx,pdf,zip,rar,txt,xml}")
    private String fileSuffix;

    @Value("${file.imageSuffix:jpeg,jpg,gif,png,ico,bmp}")
    private String imageSuffix;


    @Autowired
    private FileDao fileDao;

    @Override
    public void imageShow(Long id) {
        fileDownload(id, FileType.IMAGE);
    }


    private void fileDownload(Long fileId, FileType fileType) {

        //注意：使用multipart/form-data形式提交文件，不要这样获取request对象，最好使用入参形式
        HttpServletRequest request = RequestHolder.getRequest();
        HttpServletResponse response = RequestHolder.getResponse();


        //设置Header信息
        response.setCharacterEncoding("utf-8");
        response.setHeader("Accept-Ranges", "bytes");

        //如果带有 IF_MODIFIED_SINCE，说明客户端有缓存，告知客户端直接从缓存中取就行了
        String modified = request.getHeader(HttpHeaders.IF_MODIFIED_SINCE);
        if (modified != null) {
            logger.info("IF_MODIFIED_SINCE: {}", modified);
            //返回文件最后修改时间
            response.setHeader(HttpHeaders.LAST_MODIFIED, modified);
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
            return;
        }

        //如果第一次请求或者缓存中没有，则请求数据和文件
        File file = fileDao.queryByPk(fileId);
        if(file == null){
            logger.error(StatusCodeEnum.FILE_NOT_EXIST.getMsg());
            throw new BusinessException(StatusCodeEnum.FILE_NOT_EXIST);
        }
        //文件路径
        String filePath = file.getPath();
        //判断是否为图片
        if (fileType == FileType.IMAGE) {
            String[] suffixArray = imageSuffix.split(",");
            int lastIndex = filePath.lastIndexOf(".");
            //获取文件后缀名
            String suffix = filePath.substring(lastIndex + 1, filePath.length());
            //检验后缀是否合法
            if (!FileUploadUtil.checkFileSuffix(suffixArray, suffix)) {
                logger.error(StatusCodeEnum.IMAGE_NOT_EXIST.getMsg() + ": " + suffix);
                throw new BusinessException(StatusCodeEnum.IMAGE_NOT_EXIST);
            }
        }
        //获取文件名
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);


        response.setHeader(HttpHeaders.EXPIRES, CalendarUtil.yearOrientation(new Date(), 1).toString());
        //设置文件缓存365天
        //response.addHeader(HttpHeaders.CACHE_CONTROL, "public");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=31536000");

        Date updateTime = file.getModifiedTime();
        if(updateTime == null){
            updateTime = file.getCreateTime();
        }
        //返回文件最后修改时间
        response.setHeader(HttpHeaders.LAST_MODIFIED, updateTime.toString());

        String mimeType = MimeTypeUtil.getTypeByFileName(root + filePath);
        logger.info("mimeType: {}", mimeType);
        if(mimeType == null){
            mimeType = MimeTypeUtil.getTypeByStream(root + filePath);
        }
        logger.info("mimeType: {}", mimeType);
        response.setContentType(mimeType);//通知浏览器已什么格式打开文件


        java.io.File sourceFile = new java.io.File(root, filePath);
        if (!sourceFile.exists()) {
            logger.error(StatusCodeEnum.FILE_NOT_EXIST.getMsg());
            throw new BusinessException(StatusCodeEnum.FILE_NOT_EXIST);
        }

        BufferedInputStream bis = null;
        try {
            FileInputStream fis = new FileInputStream(sourceFile);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int len;
            byte[] buffer = new byte[2048];
            while ((len = bis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     *
     * @return 保存图片
     */
    @Override
    public Long fileUpload(FileType fileType, String fileBase64, String suffix, Integer type) {

        String[] suffixArray;
        String dir = fileDir;

        suffixArray = imageSuffix.split(",");
        if (fileType == FileType.IMAGE) {
            dir = imageDir;
        }

        //检验后缀是否合法
        if (!FileUploadUtil.checkFileSuffix(suffixArray, suffix)) {
            logger.error(StatusCodeEnum.FILE_SUFFIX_NOT_SUPPORT.getMsg() + ": " + suffix);
            throw new BusinessException(StatusCodeEnum.FILE_SUFFIX_NOT_SUPPORT);
        }

        //计算上传文件MD5和SHA1
        byte[] bytes = FileConvertUtil.base64ToByte(fileBase64);
        int size = bytes.length;

        String md5 = HashUtil.md5(bytes);
        String sha1 = HashUtil.sha1(bytes);
        logger.info("size: {}, md5: {}, sha1: {}", size, md5, sha1);

        File file = fileDao.queryByMd5AndSha1(md5, sha1);

        if(file == null){

            //文件相对目录
            dir =  dir + "/" + DateFormatUtil.now_yyyyMMdd();

            //生成文件id
            String fileId = (System.currentTimeMillis() - 936841600000L + System.currentTimeMillis()) + RandomUtil.createDigitCode(3);

            //文件名
            String fileName = fileId + "." + suffix;

            //文件路径
            String filePath = dir + "/" + fileName;

            //保存到硬盘
            boolean saveResult = FileUploadUtil.saveFile(root + dir, fileName, bytes);

            logger.info("savePath:{}", filePath);
            logger.info("saveResult:{}", saveResult);

            if(!saveResult){
                logger.error(StatusCodeEnum.FILE_UPLOAD_FAILED.getMsg());
                throw new BusinessException(StatusCodeEnum.FILE_UPLOAD_FAILED);
            }

            file = new File(Long.parseLong(fileId), filePath, (long)size, md5, sha1, type);
            if(fileDao.insert(file) == 0){
                logger.error(StatusCodeEnum.FILE_UPLOAD_FAILED.getMsg());
                throw new BusinessException(StatusCodeEnum.FILE_UPLOAD_FAILED);
            }
        }else {

            //如果硬盘上文件不存在
            java.io.File sourceFile = new java.io.File(root, file.getPath());
            if(!sourceFile.exists()){

                //文件相对目录
                dir =  dir + "/" + DateFormatUtil.now_yyyyMMdd();

                //文件名
                String fileName = file.getId() + "." + suffix;

                //文件路径
                String filePath = dir + "/" + fileName;

                //保存到硬盘
                boolean saveResult = FileUploadUtil.saveFile(root + dir, fileName, bytes);

                logger.info("savePath:{}", filePath);
                logger.info("saveResult:{}", saveResult);

                if(!saveResult){
                    logger.error(StatusCodeEnum.FILE_UPLOAD_FAILED.getMsg());
                    throw new BusinessException(StatusCodeEnum.FILE_UPLOAD_FAILED);
                }
                //更新文件路径
                file.setPath(filePath);
                if(fileDao.update(file) == 0){
                    logger.error(StatusCodeEnum.FILE_UPLOAD_FAILED.getMsg());
                    throw new BusinessException(StatusCodeEnum.FILE_UPLOAD_FAILED);
                }
            }

        }
        return file.getId();
    }
    /**
     *
     * @return 上传图片
     */
    @Override
    public Long imageForm(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                InputStream in = file.getInputStream();
                byte[] bytes = new byte[in.available()];
                int length = in.read(bytes);
                logger.debug("文件长度:{}", length);
                String base64Str = new BASE64Encoder().encode(bytes);
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(base64Str);
                String newBaseStr = m.replaceAll("");
//                base64Str = base64Str.replaceAll("\\r\\n","");
//                byte[] image = file.getBytes();
//                String base64Str = Base64Util.encrypt(new String(image, "UTF-8"));
                logger.debug("图片转成base64字符串:{}", newBaseStr);
                Long id = fileUpload(FileType.IMAGE, newBaseStr, "jpg", FileSaveType.TRAIN.getCode());
                logger.debug("图片保存的id为:{}", id);
                return id;
            } catch (IOException e) {
                logger.debug("图片上传失败");
                throw new BusinessException(StatusCodeEnum.SERVER_ERROR);
            }
        }
        logger.warn("上传图片为空");
        throw new BusinessException(StatusCodeEnum.IMAGE_IS_EMPTY);
    }


}
