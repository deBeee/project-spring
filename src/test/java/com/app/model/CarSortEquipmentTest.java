package com.app.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class CarSortEquipmentTest {
    @Test
    @DisplayName("When list of equipment is sorted correctly")
    void test1(){
        var car = Car
                .builder()
                .equipment(List.of("B", "C", "A"))
                .build();

        var expectedCar = Car
                .builder()
                .equipment(List.of("A", "B", "C"))
                .build();

        Assertions.assertThat(car.sortEquipment(Comparator.naturalOrder()))
                .isEqualTo(expectedCar);
    }

}
