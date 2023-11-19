package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.app.Cars.*;
import static com.app.model.Mappers.toColorMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class CarServiceImplGroupByAndCountByTest {
    private static final List<Car> CARS = List.of(AUDI_1_CAR, BMW_CAR, MAZDA_1_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when cars are grouped correctly by classifier")
    void test1() {
        assertThat(carService.groupBy(toColorMapper))
                .hasSize(3)
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, List.of(AUDI_1_CAR),
                        Color.WHITE, List.of(BMW_CAR),
                        Color.BLUE, List.of(MAZDA_1_CAR)
                ));
    }

    @Test
    @DisplayName("when cars are counted correctly by classifier")
    void test2() {
        assertThat(carService.countBy(toColorMapper))
                .hasSize(3)
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, 1L,
                        Color.WHITE, 1L,
                        Color.BLUE, 1L
                ));
    }
}
