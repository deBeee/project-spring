package com.app.service.impl;

import com.app.model.Car;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplFindAllBySpeedBetweenTest {
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
    private static Stream<Arguments> speedRangesWithCars() {
        return Stream.of(
                Arguments.of( 100, 150, List.of()),
                Arguments.of( 200, 210, List.of(AUDI_1_CAR)),
                Arguments.of( 200, 250, List.of(AUDI_1_CAR, BMW_CAR)),
                Arguments.of( 190, 250, List.of(AUDI_1_CAR, BMW_CAR, MAZDA_1_CAR)),
                Arguments.of( 250, 260, List.of(BMW_CAR)),
                Arguments.of( 250, 250, List.of(BMW_CAR)),
                Arguments.of( 260, 270, List.of())
        );
    }

    @Test
    @DisplayName("when speed range is not correct")
    void test1(){
        assertThatThrownBy(() -> carService.findAllBySpeedBetween(100,50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Speed range is not correct");
    }

    @ParameterizedTest
    @MethodSource("speedRangesWithCars")
    @DisplayName("when speed range is correct")
    void test2(int speedMin, int speedMax, List<Car> expectedCars){
        assertThat(carService.findAllBySpeedBetween(speedMin, speedMax))
                .isEqualTo(expectedCars);
    }
}
