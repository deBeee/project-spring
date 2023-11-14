package com.app;

import com.app.data.json.converter.impl.GsonConverter;
import com.app.data.json.deserializer.impl.CarsDataJsonDeserializer;
import com.app.data.model.CarsData;
import com.google.gson.GsonBuilder;

public class App {
    public static void main(String[] args) {
        var filename = "cars.json";
        var gson = new GsonBuilder().setPrettyPrinting().create();
        var jsonConverter = new GsonConverter<CarsData>(gson);
        var carsDataJsonDeserializer = new CarsDataJsonDeserializer(jsonConverter);
        carsDataJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);
    }
}
