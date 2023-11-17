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

public class CarServiceImplSortTest {
    private static final List<Car> CARS = List.of(AUDI_CAR, BMW_CAR, MAZDA_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

    private static Stream<Arguments> comparatorsWithSortedCars() {
        return Stream.of(
                Arguments.of(byMakeComparator, List.of(AUDI_CAR, BMW_CAR, MAZDA_CAR)),
                Arguments.of(byMakeComparatorDesc, List.of(MAZDA_CAR, BMW_CAR, AUDI_CAR)),
                Arguments.of(byPriceComparator, List.of(AUDI_CAR, MAZDA_CAR, BMW_CAR)),
                Arguments.of(byPriceComparatorDesc, List.of(BMW_CAR, MAZDA_CAR, AUDI_CAR)),
                Arguments.of(bySpeedComparator, List.of(MAZDA_CAR, AUDI_CAR, BMW_CAR)),
                Arguments.of(bySpeedComparatorDesc, List.of(BMW_CAR,  AUDI_CAR, MAZDA_CAR))
        );
    }

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        assertThatThrownBy(() -> carService.sort(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @ParameterizedTest
    @MethodSource("comparatorsWithSortedCars")
    @DisplayName("when comparator is not null")
    void test2(Comparator<Car> carComparator, List<Car> expectedSortedCars){
        assertThat(carService.sort(carComparator)).isEqualTo(expectedSortedCars);
    }
}
