package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceImplSortEquipmentTest {
    private static final List<Car> CARS = List.of(AUDI_1_CAR, BMW_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        assertThatThrownBy(() -> carService.sortEquipment(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @Test
    @DisplayName("when comparator is not null")
    void test2(){
        Comparator<String> comparator = Comparator.naturalOrder();
        List<Car> expectedCars = List.of(
                AUDI_1_CAR.sortEquipment(comparator),
                BMW_CAR.sortEquipment(comparator)
        );

        Assertions.assertThat(carService.sortEquipment(comparator))
                .isEqualTo(expectedCars);
    }

}
