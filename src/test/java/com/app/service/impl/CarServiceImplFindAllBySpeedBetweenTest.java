package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static com.app.model.Comparators.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceImplFindAllBySpeedBetweenTest {
    private static final List<Car> CARS = List.of(AUDI_CAR, BMW_CAR, MAZDA_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

    private static Stream<Arguments> speedRangesWithCars() {
        return Stream.of(
                Arguments.of( 100, 150, List.of()),
                Arguments.of( 200, 210, List.of(AUDI_CAR)),
                Arguments.of( 200, 250, List.of(AUDI_CAR, BMW_CAR)),
                Arguments.of( 190, 250, List.of(AUDI_CAR, BMW_CAR, MAZDA_CAR)),
                Arguments.of( 250, 260, List.of(BMW_CAR)),
                Arguments.of( 250, 250, List.of(BMW_CAR)),
                Arguments.of( 260, 270, List.of())
        );
    }

    @Test
    @DisplayName("when speed range is not correct")
    void test1(){
        assertThatThrownBy(() -> carService.findAllBySpeedBetween(100,50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Speed range is not correct");
    }

    @ParameterizedTest
    @MethodSource("speedRangesWithCars")
    @DisplayName("when speed range is correct")
    void test2(int speedMin, int speedMax, List<Car> expectedCars){
        assertThat(carService.findAllBySpeedBetween(speedMin, speedMax))
                .isEqualTo(expectedCars);
    }
}
