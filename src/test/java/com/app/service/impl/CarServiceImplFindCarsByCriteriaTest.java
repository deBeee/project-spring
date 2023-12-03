package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceImplFindCarsByCriteriaTest {
    private static final List<Car> CARS = List.of(BMW_CAR, AUDI_1_CAR, AUDI_2_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        assertThatThrownBy(() -> carService.findCarsByCriteria(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @Test
    @DisplayName("when the comparator checks the smallest difference between the car's price and other specified price.")
    void test2(){
        var otherPrice = BigDecimal.valueOf(8);
        Comparator<Car> carComparator = Comparator.comparing(car -> car.calculatePriceDifference(otherPrice));
        var foundCars = carService.findCarsByCriteria(carComparator);
        assertThat(foundCars).isEqualTo(List.of(BMW_CAR, AUDI_2_CAR));
    }

    @Test
    @DisplayName("when the comparator checks the smallest difference between the car's speed and other specified speed.")
    void test3(){
        int otherSpeed = 205;
        Comparator<Car> carComparator = Comparator.comparingInt(car -> car.calculateSpeedDifference(otherSpeed));
        var foundCars = carService.findCarsByCriteria(carComparator);
        assertThat(foundCars).isEqualTo(List.of(AUDI_1_CAR, AUDI_2_CAR));
    }
}
