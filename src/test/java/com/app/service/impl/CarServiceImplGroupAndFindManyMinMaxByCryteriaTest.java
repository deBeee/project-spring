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
public class CarServiceImplGroupAndFindManyMinMaxByCryteriaTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void beforeEach() {
        var CARS = List.of(AUDI_1_CAR, AUDI_2_CAR, AUDI_3_CAR, BMW_CAR, MAZDA_1_CAR, MAZDA_2_CAR);
        when(carRepository.getCars())
                .thenReturn(CARS);
    }

    @Test
    @DisplayName("when grouping function is null")
    void test1(){
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                null, toPriceMapper, naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grouping function is null");
    }

    @Test
    @DisplayName("when minMaxGrouping function is null")
    void test2(){
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                toMakeMapper, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Min max grouping function is null");
    }

    @Test
    @DisplayName("when grouping function is null")
    void test3(){
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                toMakeMapper, toPriceMapper, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Min max comparator is null");
    }

    @Test
    @DisplayName("should group by make and find at least one min and at least one max by price")
    void test4(){
        assertThat(carService.groupAndFindMinMaxByCriteria(
                toMakeMapper, toPriceMapper, naturalOrder()))
                .containsAllEntriesOf(Map.of(
                        "AUDI", new MinMax<>(List.of(AUDI_1_CAR), List.of(AUDI_2_CAR, AUDI_3_CAR)),
                        "BMW", new MinMax<>(List.of(BMW_CAR), List.of(BMW_CAR)),
                        "MAZDA", new MinMax<>(List.of(MAZDA_2_CAR), List.of(MAZDA_1_CAR))
                ));
    }

    @Test
    @DisplayName("should group by color and find at least one min and at least one max by speed")
    void test5(){
        assertThat(carService.groupAndFindMinMaxByCriteria(
                toColorMapper, toSpeedMapper, naturalOrder()))
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, new MinMax<>(List.of(AUDI_1_CAR), List.of(AUDI_2_CAR, AUDI_3_CAR)),
                        Color.WHITE, new MinMax<>(List.of(BMW_CAR), List.of(BMW_CAR)),
                        Color.BLUE, new MinMax<>(List.of(MAZDA_1_CAR), List.of(MAZDA_1_CAR))
                ));
    }

}
