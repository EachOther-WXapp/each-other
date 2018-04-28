package com.wutong.weixin.utils.exception;

import com.wutong.weixin.utils.response.ResponseMessage;
import com.wutong.weixin.utils.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.ConnectException;

/**
 *
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：
 * <pre>
 * NullPointerException,
 * RunTimeException,
 * ClassCastException,
 * NoSuchMethodException,
 * IOException,
 * IndexOutOfBoundsException
 * 以及springmvc自定义异常等，如下：
 * 自定义异常Exception                       	HTTP Status Code
 * ConversionNotSupportedException         500 (Internal Server Error)
 * HttpMessageNotWritableException         500 (Internal Server Error)
 * HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
 * HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException    404 (Not Found)
 * TypeMismatchException                   400 (Bad Request)
 * HttpMessageNotReadableException         400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 * </pre>
 */
@RestControllerAdvice
public class RestExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String DEFAULT_ERROR_MESSAGE = "内部错误";



    //HTTP请求异常（ServletException）
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,//HTTP请求方法不支持
            HttpMediaTypeNotSupportedException.class,//HTTP请求Media类型不支持
            HttpMediaTypeNotAcceptableException.class,//HTTP请求Media类型不认可
            MissingPathVariableException.class,//path路径中变量缺失，通常由于URI模板与方法参数声明的PATH变量名不匹配，属于代码问题
            MissingServletRequestParameterException.class,//缺失Request请求参数
            ServletRequestBindingException.class,//请求参数绑定异常
            ConversionNotSupportedException.class,//类型转换不支持异常
            TypeMismatchException.class,//类型不匹配异常
            HttpMessageNotReadableException.class,//HttpMessageConverter,消息不可读异常 request
            HttpMessageNotWritableException.class,//HttpMessageConverter,消息不可写异常 response
            MethodArgumentNotValidException.class,//Valid参数校验异常
            ConstraintViolationException.class,//Validated Request请求参数校验
            MissingServletRequestPartException.class,//缺失请求part
            BindException.class,//绑定BindingResult出现致命错误时抛出
            NoHandlerFoundException.class,//找不到对应处理异常
            AsyncRequestTimeoutException.class//异步请求超时异常
    })
    public ResponseMessage handleHttpException(Exception ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        if(ex instanceof HttpRequestMethodNotSupportedException){
            //HTTP请求方法不支持
            return ResponseUtil.error(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
        }else if(ex instanceof HttpMediaTypeNotSupportedException){
            //HTTP请求Media类型不支持
            return ResponseUtil.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());
        }else if(ex instanceof HttpMediaTypeNotAcceptableException){
            //HTTP请求Media类型不认可
            return ResponseUtil.error(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
        }else if(ex instanceof MissingPathVariableException){
            //path路径中变量缺失，通常由于URI模板与方法参数声明的PATH变量名不匹配，属于代码问题
            ex.printStackTrace();
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MESSAGE);
        }else if(ex instanceof MissingServletRequestParameterException){
            //缺失request请求参数
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }else if(ex instanceof ServletRequestBindingException){
            //请求参数绑定异常
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }else if(ex instanceof ConversionNotSupportedException){
            //类型转换不支持异常
            ex.printStackTrace();
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MESSAGE);
        }else if(ex instanceof TypeMismatchException){
            //类型不匹配异常
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }else if(ex instanceof HttpMessageNotReadableException){
            //HttpMessageConverter,消息不可读异常 request
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }else if(ex instanceof HttpMessageNotWritableException){
            //HttpMessageConverter,消息不可写异常 response
            ex.printStackTrace();
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MESSAGE);
        }else if(ex instanceof MethodArgumentNotValidException){
            //Valid参数校验异常
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),
                    ((MethodArgumentNotValidException)ex).getBindingResult().getFieldError().getDefaultMessage());
        }else if(ex instanceof ConstraintViolationException){
            //Validated Request请求参数校验
            ConstraintViolationException violationException = (ConstraintViolationException) ex;
            if (violationException.getConstraintViolations() == null){
                return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),"参数缺失或不正确");
            }
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),
                    violationException.getConstraintViolations().iterator().next().getMessage());
        }else if(ex instanceof MissingServletRequestPartException){
            //缺失请求part
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }else if(ex instanceof BindException){
            //绑定BindingResult出现致命错误时抛出
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }else if(ex instanceof NoHandlerFoundException){
            //找不到对应处理异常
            return ResponseUtil.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        }else if(ex instanceof AsyncRequestTimeoutException){
            //异步请求超时异常
            ex.printStackTrace();
            return ResponseUtil.error(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage());
        }else {
            //未知异常
            ex.printStackTrace();
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MESSAGE);
        }
    }


    //自定义运行时异常
    @ExceptionHandler(BaseException.class)
    public ResponseMessage runtimeExceptionHandler(BaseException ex) {
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(ex.getStatus(), ex.getMessage());
    }


    //常见异常处理，对外统一错误码：500，错误信息：未知错误，详细错误记录到日志中
    @ExceptionHandler({
            NullPointerException.class,//空指针异常（运行时异常）
            ClassCastException.class,//类型转换异常（运行时异常）
            IndexOutOfBoundsException.class,//数组越界异常（运行时异常）
            NoSuchMethodException.class,//未知方法异常，反射中可能出现
            ConnectException.class,//网络连接异常（IO异常）
            IOException.class,//IO异常
            RuntimeException.class,//运行时异常
            Exception.class//未知异常
    })
    public ResponseMessage handleException(Exception ex) {
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        ex.printStackTrace();
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }


/*
    //HTTP请求方法不存在异常（ServletException）
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseMessage handleException(HttpRequestMethodNotSupportedException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.METHOD_NOT_ALLOWED.value(),ex.getMessage());
    }

    //HTTP请求Media类型不支持异常（ServletException）
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseMessage handleException(HttpMediaTypeNotSupportedException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),ex.getMessage());
    }

    //HTTP请求Media类型异常（ServletException）
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseMessage handleException(HttpMediaTypeNotAcceptableException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
    }


    //HTTP请求参数异常（运行时异常）
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseMessage handleException(MissingServletRequestParameterException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    //HTTP请求消息不可读异常（运行时异常）
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseMessage requestNotReadable(HttpMessageNotReadableException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),"HttpMessageNotReadableException");
    }

    //请求消息不可写异常（运行时异常）
    @ExceptionHandler({HttpMessageNotWritableException.class})
    public ResponseMessage httpMessageNotWritable(RuntimeException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error("HttpMessageNotWritableException");
    }
    // @RequestParam 注解时参数效验异常
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseMessage runtimeExceptionHandler(ConstraintViolationException e){
        if (e.getConstraintViolations() == null){
            return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),"参数缺失或不正确");
        }
        logger.error("ConstraintViolationException {}",e.getConstraintViolations().iterator().next().getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),e.getConstraintViolations().iterator().next().getMessage());
    }
    // @RequestBody 注解时参数效验错误异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMessage runtimeExceptionHandler(MethodArgumentNotValidException ex){
        logger.error("BindException {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    //类型转换不支持异常（TypeMismatchException）
    @ExceptionHandler({ConversionNotSupportedException.class})
    public ResponseMessage conversionNotSupported(RuntimeException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error("ConversionNotSupportedException");
    }

    //类型不匹配异常（运行时异常）
    @ExceptionHandler({TypeMismatchException.class})
    public ResponseMessage requestTypeMismatch(TypeMismatchException ex){
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),"TypeMismatchException");
    }
*/





/*
    //空指针异常（运行时异常）
    @ExceptionHandler(NullPointerException.class)
    public ResponseMessage handleException(NullPointerException ex) {
        logger.error("{}：{}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }

    //类型转换异常（运行时异常）
    @ExceptionHandler(ClassCastException.class)
    public ResponseMessage classCastExceptionHandler(ClassCastException ex) {
        logger.error("ClassCastException {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }

    //数组越界异常（运行时异常）
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseMessage indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        logger.error("IndexOutOfBoundsException {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }

    //未知方法异常，反射中可能出现
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseMessage noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        logger.error("NoSuchMethodException {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }

    //网络连接异常（IO异常）
    @ExceptionHandler(ConnectException.class)
    public ResponseMessage connectExceptionHandler(ConnectException ex) {
        logger.error("ConnectException {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ResponseMessage iOExceptionHandler(IOException ex) {
        logger.error("IOException {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),DEFAULT_ERROR_MESSAGE);
    }

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseMessage runtimeExceptionHandler(RuntimeException ex) {
        logger.error("RuntimeException {}", ex.getMessage());
        return ResponseUtil.error(ex.getMessage());
    }

    //系统异常
    @ExceptionHandler(Exception.class)
    public ResponseMessage runtimeExceptionHandler(Exception ex) {
        logger.error("Exception {}", ex.getMessage());
        return ResponseUtil.error("Server Exception");
    }
*/


}
