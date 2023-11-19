package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import com.app.util.MinMax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.app.Cars.*;
import static com.app.model.Comparators.byPriceComparator;
import static com.app.model.Comparators.bySpeedComparator;
import static com.app.model.Mappers.toColorMapper;
import static com.app.model.Mappers.toMakeMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class CarServiceImplGroupAndFindMinMaxByCryteriaTest {
    private static final List<Car> CARS = List.of(AUDI_1_CAR, AUDI_2_CAR, BMW_CAR, MAZDA_1_CAR, MAZDA_2_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);


    @Test
    @DisplayName("should group by make and find min and max by price")
    void test1(){
        assertThat(carService.groupAndFindMinMaxByCriteria(toMakeMapper, byPriceComparator))
                .containsAllEntriesOf(Map.of(
                        "AUDI", new MinMax<>(AUDI_1_CAR, AUDI_2_CAR),
                        "BMW", new MinMax<>(BMW_CAR, BMW_CAR),
                        "MAZDA", new MinMax<>(MAZDA_2_CAR, MAZDA_1_CAR)
                ));
    }

    @Test
    @DisplayName("should group by color and find min and max by speed")
    void test2(){
        assertThat(carService.groupAndFindMinMaxByCriteria(toColorMapper, bySpeedComparator))
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, new MinMax<>(AUDI_1_CAR, AUDI_2_CAR),
                        Color.WHITE, new MinMax<>(BMW_CAR, BMW_CAR),
                        Color.BLUE, new MinMax<>(MAZDA_1_CAR, MAZDA_1_CAR)
                ));
    }

}
