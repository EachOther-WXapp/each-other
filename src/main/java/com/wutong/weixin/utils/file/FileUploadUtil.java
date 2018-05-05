package com.wutong.weixin.utils.file;

import com.wutong.weixin.utils.date.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具类
 */
public class FileUploadUtil {

	private final static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

	
	/**
	 * 获取项目根目录相对路径
	 * @param request
	 * @return
	 */
	public static String getRootDir(HttpServletRequest request){
		return request.getServletContext().getContextPath();
	}
	
	/**
	 * 获取项目根目录绝对路径
	 * @param request
	 * @return
	 */
	public static String getRootRealDir(HttpServletRequest request){
		return request.getServletContext().getRealPath("/");
	}
	
	/**
	 * 检测目录是否存在,不存在则创建
	 * @param dir 
	 * @return
	 */
	public static boolean checkDir(String dir){
		
		File f = new File(dir);
		//判断文件夹是否存在
		if (f.exists()) {
			return true;
		}
		//创建文件夹
		return f.mkdirs();
	}


	/**
	 * 文件保存
	 * @param fileDir 文件目录
	 * @param fileName 文件名
	 * @param bytes  字节数组
	 * @return
	 */
	public static boolean saveFile(String fileDir, String fileName, byte[] bytes){
		//检测硬盘目录是否存在,不存在则创建
		checkDir(fileDir);
		//字节数组保存文件
		return FileConvertUtil.byteToFile(fileDir + "/" + fileName, bytes);
	}



    /**
     * 表单文件保存
     *
     * @param root
     * @param fileDir
     * @param file
     * @return
     */
	public static String saveFile(String root, String fileDir, MultipartFile file){

		if(null == file || file.isEmpty()) return null;
        //文件后缀
        String suffix = getFileSuffix(file.getOriginalFilename());
        if(null == suffix || suffix.equals("")) return null;

		//文件目录            /upload/images  /   20171101
		fileDir =  fileDir + "/" + DateFormatUtil.now_yyyyMMdd();

		//检测硬盘目录是否存在,不存在则创建
		checkDir(root + fileDir);

		//文件名
		String fileName = fileDir + "/" + System.currentTimeMillis() + "." + suffix;

		boolean saveResult = false;
		try {
			file.transferTo(new File(fileName));
			saveResult = true;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		logger.info("saveResult:{}", saveResult);
		logger.info("savePath:{}", root + fileName);
		return saveResult ? fileName : null;
	}




	/**
	 * 检测文件后缀是否合法
	 * @param suffixs   文件后缀数组
	 * @param targetSuffix 校验的目标后缀名
	 * @return
	 */
    public static boolean checkFileSuffix(String[] suffixs, String targetSuffix) {
    	if (targetSuffix == null){
    		return false;
		}
        for (String suffix : suffixs) {
            if (targetSuffix.toLowerCase().equals(suffix.trim()))
                return true;
        }
        return false;
    }



	
	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		int lastIndex = fileName.lastIndexOf(".");
		if(lastIndex < 1) return null;
		return fileName.substring(lastIndex + 1, fileName.length());
	}
	
	
}
