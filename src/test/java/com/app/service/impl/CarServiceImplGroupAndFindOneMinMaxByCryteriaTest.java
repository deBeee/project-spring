package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import com.app.util.MinMax;
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
import java.util.Map;

import static com.app.Cars.*;
import static com.app.model.Comparators.byPriceComparator;
import static com.app.model.Comparators.bySpeedComparator;
import static com.app.model.Mappers.*;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplGroupAndFindOneMinMaxByCryteriaTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void beforeEach() {
        var CARS = List.of(AUDI_1_CAR, AUDI_2_CAR, BMW_CAR, MAZDA_1_CAR, MAZDA_2_CAR);
        when(carRepository.getCars())
                .thenReturn(CARS);
    }

    @Test
    @DisplayName("when grouping function is null")
    void test1(){
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                null, byPriceComparator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grouping function is null");
    }

    @Test
    @DisplayName("when carComparator function is null")
    void test2(){
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                toMakeMapper, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car comparator is null");
    }

    @Test
    @DisplayName("should group by make and find one min and one max by price")
    void test3(){
        assertThat(carService.groupAndFindMinMaxByCriteria(toMakeMapper, byPriceComparator))
                .containsAllEntriesOf(Map.of(
                        "AUDI", new MinMax<>(AUDI_1_CAR, AUDI_2_CAR),
                        "BMW", new MinMax<>(BMW_CAR, BMW_CAR),
                        "MAZDA", new MinMax<>(MAZDA_2_CAR, MAZDA_1_CAR)
                ));
    }

    @Test
    @DisplayName("should group by color and find one min and one max by speed")
    void test4(){
        assertThat(carService.groupAndFindMinMaxByCriteria(toColorMapper, bySpeedComparator))
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, new MinMax<>(AUDI_1_CAR, AUDI_2_CAR),
                        Color.WHITE, new MinMax<>(BMW_CAR, BMW_CAR),
                        Color.BLUE, new MinMax<>(MAZDA_1_CAR, MAZDA_1_CAR)
                ));
    }
}
