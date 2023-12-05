package com.app.repository.impl;

import com.app.Cars;
import com.app.converter.car.FileToCarsConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.app.Cars.AUDI_1_CAR;
import static com.app.Cars.BMW_CAR;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarRepositoryImplGetCarsTest {
    @Mock
    private FileToCarsConverter fileToCarsConverter;

    private CarRepositoryImpl carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        var cars = List.of(AUDI_1_CAR, BMW_CAR);
        var FILENAME = "cars.json";
        when(fileToCarsConverter.convert(FILENAME))
                .thenReturn(cars);

        carRepository = new CarRepositoryImpl(fileToCarsConverter);
        carRepository.filename = FILENAME;
        carRepository.init();
    }

    @Test
    void test1() {
        assertThat(carRepository.getCars())
                .hasSize(2)
                .isEqualTo(List.of(AUDI_1_CAR, BMW_CAR));
    }
}
