package com.app;

import com.app.config.AppBeansConfig;
import com.app.data.json.deserializer.impl.CarsDataJsonDeserializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppBeansConfig.class);
        var filename = "cars.json";
        var carsDataJsonDeserializer
                = context.getBean("carsDataJsonDeserializer", CarsDataJsonDeserializer.class);
        carsDataJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);
    }
}
