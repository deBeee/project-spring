package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class Car {
    final String make;
    final String model;
    final int speed;
    final BigDecimal price;
    final Color color;
    final List<String> equipment;

    public boolean hasSpeedBetween(int speedMin, int speedMax) {
        return speedMin <= speed && speed <= speedMax;
    }
    public Car sortEquipment(Comparator<String> equipmentComparator) {
        return Car
                .builder()
                .make(make)
                .model(model)
                .speed(speed)
                .color(color)
                .price(price)
                .equipment(equipment.stream().sorted(equipmentComparator).toList())
                .build();
    }
}
