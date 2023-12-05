package com.app.service.impl;

import com.app.model.Car;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import com.app.util.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static com.app.model.Color.*;
import static com.app.model.Mappers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplGetStatisticsTest {
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
    private static Stream<Arguments> extractorsWithStatistics() {
        return Stream.of(
                Arguments.of(toColorMapper, new Statistics<>(BLACK, BLUE, BigDecimal.ZERO)),
                Arguments.of(toPriceMapper, new Statistics<>(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(4))),
                Arguments.of(toMakeMapper, new Statistics<>("AUDI", "MAZDA", BigDecimal.ZERO)),
                Arguments.of(toSpeedMapper, new Statistics<>(190, 250, BigDecimal.valueOf(213)))
        );
    }

    @Test
    @DisplayName("when extractor is null")
    void test1(){
        assertThatThrownBy(() -> carService.getStatistics(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Extractor is null");
    }

    @SuppressWarnings("rawtypes")
    @ParameterizedTest
    @MethodSource("extractorsWithStatistics")
    @DisplayName("when extractor is not null")
    void test2(Function extractor, Statistics<?> statistics){
        assertThat(carService.getStatistics(extractor)).isEqualTo(statistics);
    }
}
