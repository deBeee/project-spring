package com.app.data.model;

import com.app.model.Car;
import com.app.model.Color;

import java.math.BigDecimal;
import java.util.List;

public record CarData(
        String make,
        String model,
        int speed,
        Color color,
        BigDecimal price,
        List<String>equipment
) {
    public Car toCar() {
        return Car
                .builder()
                .make(make)
                .model(model)
                .speed(speed)
                .color(color)
                .price(price)
                .equipment(equipment)
                .build();
    }
}
