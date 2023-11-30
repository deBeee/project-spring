package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import com.app.util.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static com.app.model.Color.*;
import static com.app.model.Mappers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceImplGetStatisticsTest {
    private static final List<Car> CARS = List.of(AUDI_1_CAR, BMW_CAR, MAZDA_1_CAR);
    private static final CarService carService = new CarServiceImpl(CARS);

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
