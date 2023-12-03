package com.app;

import com.app.config.AppBeansConfig;
import com.app.service.impl.EmailServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppBeansConfig.class);
        /*var filename = "cars.json";
        var carsDataJsonDeserializer
                = context.getBean("carsDataJsonDeserializer", CarsDataJsonDeserializer.class);
        carsDataJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);*/

        var emailService = context.getBean("emailServiceImpl", EmailServiceImpl.class);
        emailService.send("testowydoaplikacji2000@gmail.com", "TOPIC", "CONTENT");
    }
}
