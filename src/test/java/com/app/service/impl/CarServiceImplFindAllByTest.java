package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.model.Predicates;
import com.app.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static com.app.model.Mappers.toColorMapper;
import static com.app.model.Predicates.hasSpeedBetweenPredicate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceImplFindAllByTest {
    private static final List<Car> CARS = List.of(AUDI_CAR, BMW_CAR, MAZDA_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);


    @org.junit.jupiter.api.Test
    @DisplayName("when cars don't contain speed in range")
    void test1() {
        assertThat(carService.findAllBy(
                Predicates.hasSpeedBetweenPredicate(100, 150)))
                .isEmpty();

    }

    @org.junit.jupiter.api.Test
    @DisplayName("when cars contain speed in range")
    void test2() {
        assertThat(carService.findAllBy(
                Predicates.hasSpeedBetweenPredicate(200, 300)))
                .isEqualTo(List.of(AUDI_CAR, BMW_CAR));
    }
}
