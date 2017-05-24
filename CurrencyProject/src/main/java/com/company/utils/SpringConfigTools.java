package com.company.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring容器 bean对象
 * Created by yzw on 2017/4/26.
 */
public class SpringConfigTools implements ApplicationContextAware {

    private static ApplicationContext context = null;
    private static SpringConfigTools stools = null;

    public synchronized static SpringConfigTools init() {
        if (stools == null) {
            stools = new SpringConfigTools();
        }
        return stools;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

}
