package com.wutong.weixin.utils.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wutong.weixin.utils.exception.BaseException;

import java.io.IOException;
import java.util.List;

/**
 *
 * json和对象互转
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        //遇到未知属性时是否抛异常，这里设置不抛异常，忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    /**
     * json字符串转对象
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * json字符串转list
     * @param content
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String content, Class<T> clazz){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return objectMapper.readValue(content, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对象转json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * JsonNode转对象
     * @param jsonNode json节点
     * @param typeReference 需要转换的类型
     * @param <T>   需要转换的类型
     * @return 对象
     */
    public static <T> T readValue(JsonNode jsonNode, TypeReference<T> typeReference){

        if(jsonNode == null){
            throw new BaseException("Json cannot null !");
        }

        int code = jsonNode.get("code").intValue();
        if (code == 200 ){
            try {
                return objectMapper.readValue(jsonNode.get("data").toString(), typeReference);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseException("Json parsing error !");
            }
        }else{
            String msg = jsonNode.get("msg").asText();
            throw new BaseException(msg != null ? msg : "Json parsing error !");
        }

    }


    /**
     * json字符串转对象
     * @param json json字符串
     * @param typeReference  需要转换的类型
     * @param <T> 需要转换的类型
     * @return 对象
     */
    public static <T> T readValue(String json, TypeReference<T> typeReference){

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("Json parsing error !");
        }
        return readValue(jsonNode, typeReference);

    }


}
