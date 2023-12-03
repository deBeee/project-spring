package com.app.model;

import com.app.util.CarCriteria;

import java.util.function.Predicate;

public interface Predicates {
    static Predicate<Car> hasSpeedBetweenPredicate(int speedMin, int speedMax) {
        return car -> car.hasSpeedBetween(speedMin, speedMax);
    }

    static Predicate<Car> matchesCriteriaPredicate(CarCriteria carCriteria) {
        return car -> car.matchesCriteria(carCriteria);
    }
}
