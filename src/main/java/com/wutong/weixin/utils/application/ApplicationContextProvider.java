package com.wutong.weixin.utils.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextProvider.class);

    private static ApplicationContext context = null;


    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("ApplicationContext init success !!!!");
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        if(!isInit()) return null;
        return context.getBean(tClass);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if(!isInit()) return null;
        return (T) context.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        if(!isInit()) return null;
        return context.getBean(name, tClass);
    }

    private static boolean isInit() {
        if (context == null) {
            //logger.info("ApplicationContext not yet init !!!!");
            return false;
        }
        return true;
    }
}
