package com.app;

import com.app.config.AppBeansConfig;
import com.app.service.impl.CarServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppBeansConfig.class);
        var carService = context.getBean("carServiceImpl", CarServiceImpl.class);
        carService.sendReport("testowydoaplikacji2000@gmail.com", "Report");
    }
}
