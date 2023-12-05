package com.app.service.impl;

import com.app.model.Car;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Comparator;
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplSortEquipmentTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void beforeEach() {
        var CARS = List.of(AUDI_1_CAR, BMW_CAR);
        when(carRepository.getCars())
                .thenReturn(CARS);
    }

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
