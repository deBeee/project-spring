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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static com.app.model.Comparators.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplSortTest {
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

    private static Stream<Arguments> comparatorsWithSortedCars() {
        return Stream.of(
                Arguments.of(byMakeComparator, List.of(AUDI_1_CAR, BMW_CAR, MAZDA_1_CAR)),
                Arguments.of(byMakeComparatorDesc, List.of(MAZDA_1_CAR, BMW_CAR, AUDI_1_CAR)),
                Arguments.of(byPriceComparator, List.of(AUDI_1_CAR, MAZDA_1_CAR, BMW_CAR)),
                Arguments.of(byPriceComparatorDesc, List.of(BMW_CAR, MAZDA_1_CAR, AUDI_1_CAR)),
                Arguments.of(bySpeedComparator, List.of(MAZDA_1_CAR, AUDI_1_CAR, BMW_CAR)),
                Arguments.of(bySpeedComparatorDesc, List.of(BMW_CAR, AUDI_1_CAR, MAZDA_1_CAR))
        );
    }

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        assertThatThrownBy(() -> carService.sort(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @ParameterizedTest
    @MethodSource("comparatorsWithSortedCars")
    @DisplayName("when comparator is not null")
    void test2(Comparator<Car> carComparator, List<Car> expectedSortedCars){
        assertThat(carService.sort(carComparator)).isEqualTo(expectedSortedCars);
    }
}
