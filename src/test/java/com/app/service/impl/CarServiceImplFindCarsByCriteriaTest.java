package com.app.service.impl;

import com.app.model.Car;
import com.app.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplFindCarsByCriteriaTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void beforeEach() {
        var CARS = List.of(BMW_CAR, AUDI_1_CAR, AUDI_2_CAR);
        when(carRepository.getCars())
                .thenReturn(CARS);
    }

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        assertThatThrownBy(() -> carService.findCarsClosestToCriteria(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @Test
    @DisplayName("when the comparator checks the smallest difference between the car's price and other specified price.")
    void test2(){
        var otherPrice = BigDecimal.valueOf(8);
        Comparator<Car> carComparator = Comparator.comparing(car -> car.calculatePriceDifference(otherPrice));
        var foundCars = carService.findCarsClosestToCriteria(carComparator);
        assertThat(foundCars).isEqualTo(List.of(BMW_CAR, AUDI_2_CAR));
    }

    @Test
    @DisplayName("when the comparator checks the smallest difference between the car's speed and other specified speed.")
    void test3(){
        int otherSpeed = 205;
        Comparator<Car> carComparator = Comparator.comparingInt(car -> car.calculateSpeedDifference(otherSpeed));
        var foundCars = carService.findCarsClosestToCriteria(carComparator);
        assertThat(foundCars).isEqualTo(List.of(AUDI_1_CAR, AUDI_2_CAR));
    }
}
