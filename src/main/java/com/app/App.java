package com.app;

import com.app.config.AppBeansConfig;
import com.app.data.json.deserializer.impl.CarsDataJsonDeserializer;
import com.app.model.Car;
import com.app.model.Color;
import com.app.service.impl.EmailServiceImpl;
import com.app.service.impl.HtmlServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppBeansConfig.class);
        /*var filename = "cars.json";
        var carsDataJsonDeserializer
                = context.getBean("carsDataJsonDeserializer", CarsDataJsonDeserializer.class);
        carsDataJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);*/

        /*var emailService = context.getBean("emailServiceImpl", EmailServiceImpl.class);
        emailService.send("testowydoaplikacji2000@gmail.com", "TOPIC", "CONTENT");*/

        var htmlService = context.getBean("htmlServiceImpl", HtmlServiceImpl.class);
        System.out.println(htmlService.toHtml(
                "Car",
                Car
                        .builder()
                        .make("MAZDA")
                        .model("3")
                        .speed(100)
                        .price(BigDecimal.TEN)
                        .color(Color.BLACK)
                        .equipment(List.of("A", "B"))
                        .build()));

        System.out.println(htmlService.toHtmlMany(
                "Cars",
                List.of(
                        Car
                                .builder()
                                .make("MAZDA")
                                .model("3")
                                .speed(100)
                                .price(BigDecimal.TEN)
                                .color(Color.BLACK)
                                .equipment(List.of("A", "B"))
                                .build(),
                        Car
                                .builder()
                                .make("MAZDA")
                                .model("3")
                                .speed(100)
                                .price(BigDecimal.TEN)
                                .color(Color.BLACK)
                                .equipment(List.of("A", "B"))
                                .build()
                )
        ));

        System.out.println(htmlService.toHtmlPairs("Cars", Map.of(
                "A", List.of(Car
                        .builder()
                        .make("MAZDA")
                        .model("3")
                        .speed(100)
                        .price(BigDecimal.TEN)
                        .color(Color.BLACK)
                        .equipment(List.of("A", "B"))
                        .build()),
                "B", List.of(Car
                        .builder()
                        .make("MAZDA")
                        .model("3")
                        .speed(100)
                        .price(BigDecimal.TEN)
                        .color(Color.BLACK)
                        .equipment(List.of("A", "B"))
                        .build())
        )));
    }

}
