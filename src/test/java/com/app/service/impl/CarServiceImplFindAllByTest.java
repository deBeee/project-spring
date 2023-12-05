package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.model.Predicates;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import com.app.util.CarCriteria;
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
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplFindAllByTest {
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
    @DisplayName("when cars don't contain speed in range")
    void test1() {
        assertThat(carService.findAllBy(
                Predicates.hasSpeedBetweenPredicate(100, 150)))
                .isEmpty();

    }

    @Test
    @DisplayName("when cars contain speed in range")
    void test2() {
        assertThat(carService.findAllBy(
                Predicates.hasSpeedBetweenPredicate(200, 300)))
                .isEqualTo(List.of(AUDI_1_CAR, BMW_CAR));
    }

    @Test
    @DisplayName("when cars matches criteria")
    void test3() {
        CarCriteria carCriteria = new CarCriteria(
                "^M.*",
                ".*M$",
                100,
                200,
                BigDecimal.ONE,
                BigDecimal.TEN,
                List.of("B", "C"),
                Color.BLUE
        );
        assertThat(carService.findAllBy(
                Predicates.matchesCriteriaPredicate(carCriteria)))
                .hasSize(1)
                .isEqualTo(List.of(MAZDA_1_CAR));
    }

}
