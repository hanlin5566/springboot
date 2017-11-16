package com.hzcf.operation.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Create by hanlin on 2017年11月16日
 **/
public class SpringUtils implements ApplicationContextAware {  
    private static ApplicationContext context;  
  
    public static ApplicationContext getApplicationContext() {  
        return context;  
    }  
  
    @Override  
    public void setApplicationContext(ApplicationContext ac)  
            throws BeansException {  
        context = ac;  
    }  
  
    public static <T> T getBean(Class<T> tClass) {  
        return context.getBean(tClass);  
    }  
  
    public static <T> T getBean(String name, Class<T> tClass) {  
        return context.getBean(name, tClass);  
    }  
}  
