package com.app.model;

import java.util.function.Function;

public interface Mappers {
    Function<Car, Color> toColorMapper = car -> car.color;
}
