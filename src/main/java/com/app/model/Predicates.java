package com.app.model;

import java.util.function.Predicate;

public interface Predicates {
    static Predicate<Car> hasSpeedBetweenPredicate(int speedMin, int speedMax) {
        return car -> car.hasSpeedBetween(speedMin, speedMax);
    }
}
