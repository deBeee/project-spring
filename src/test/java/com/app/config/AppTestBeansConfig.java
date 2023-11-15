package com.app.config;

import com.app.data.json.converter.JsonConverter;
import com.app.data.json.converter.impl.GsonConverter;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.json.deserializer.impl.CarsDataJsonDeserializer;
import com.app.data.model.CarsData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.xml.catalog.Catalog;

@Configuration
@ComponentScan("com.app")
public class AppTestBeansConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public JsonConverter<CarsData> jsonConverter(Gson gson) {
        return new GsonConverter<>(gson);
    }

    @Bean
    public JsonDeserializer<CarsData> jsonDeserializer(JsonConverter<CarsData> jsonConverter) {
        return new CarsDataJsonDeserializer(jsonConverter);
    }
}
