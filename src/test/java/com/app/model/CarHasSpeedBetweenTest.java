package com.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CarHasSpeedBetweenTest {

    private static Stream<Arguments> speedRangesWithCars() {
        return Stream.of(
                Arguments.of(100, 150, MAZDA_1_CAR, false),
                Arguments.of(200, 210, AUDI_1_CAR, true),
                Arguments.of(200, 250, BMW_CAR, true),
                Arguments.of(190, 250, AUDI_1_CAR, true),
                Arguments.of(250, 250, BMW_CAR, true),
                Arguments.of(260, 270, BMW_CAR, false)
        );
    }

    @ParameterizedTest
    @MethodSource("speedRangesWithCars")
    @DisplayName("should check if car's speed falls within the specified range")
    void test1(int speedMin, int speedMax, Car car, boolean result) {
        assertThat(car.hasSpeedBetween(speedMin, speedMax))
                .isEqualTo(result);
    }
}
