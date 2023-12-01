package com.app.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public interface Mappers {
    Function<Car, Color> toColorMapper = car -> car.color;
    Function<Car, String> toMakeMapper = car -> car.make;
    Function<Car, BigDecimal> toPriceMapper = car -> car.price;
    Function<Car, Integer> toSpeedMapper = car -> car.speed;
    Function<Car, List<String>> toEquipmentMapper = car -> car.equipment;
}
