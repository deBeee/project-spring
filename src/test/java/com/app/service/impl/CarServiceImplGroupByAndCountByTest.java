package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Map;

import static com.app.Cars.*;
import static com.app.model.Mappers.toColorMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplGroupByAndCountByTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void beforeEach() {
        var CARS = List.of(AUDI_1_CAR, BMW_CAR, MAZDA_1_CAR);
        when(carRepository.getCars())
                .thenReturn(CARS);
    }

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
