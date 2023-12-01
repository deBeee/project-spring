package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.app.Cars.AUDI_1_CAR;
import static com.app.Cars.BMW_CAR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceImplGroupByComponentTest {
    private static final List<Car> CARS = List.of(AUDI_1_CAR, BMW_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        assertThatThrownBy(() -> carService.groupByComponent(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @Test
    @DisplayName("when comparator is not null")
    void test2(){
//        Comparator<List<Car>> comparator = (cars1, cars2) -> Integer.compare(cars1.size(), cars2.size());
        Comparator<List<Car>> comparator = Comparator.comparingInt(List::size);

        Map<String, List<Car>> expectedGroupedCars = Map.of(
                "A", List.of(AUDI_1_CAR, BMW_CAR),
                "B", List.of(AUDI_1_CAR, BMW_CAR),
                "C", List.of(BMW_CAR)
        );

        Assertions.assertThat(carService.groupByComponent(comparator)).isEqualTo(expectedGroupedCars);
    }

}
