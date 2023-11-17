package com.app.converter.car.impl;

import com.app.config.AppTestBeansConfig;
import com.app.converter.car.FileToCarsConverter;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.model.CarData;
import com.app.data.model.CarsData;
import com.app.model.Color;
import com.app.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class JsonFileToCarsConverterImplTest {

    @Autowired
    private Validator<CarData> validator;

    @Mock
    private JsonDeserializer<CarsData> carsDataJsonDeserializer;

    private FileToCarsConverter fileToCarsConverter;

    @BeforeEach
    void setUp() {
        fileToCarsConverter = new JsonFileToCarsConverterImpl(carsDataJsonDeserializer, validator);
    }

    @Test
    @DisplayName("when data all data is correct")
    void test1() {
        Mockito.when(carsDataJsonDeserializer.fromJson(ArgumentMatchers.anyString()))
                .thenReturn(new CarsData(List.of(AUDI_CAR_DATA, BMW_CAR_DATA)));

        assertThat(fileToCarsConverter.convert("cars.json"))
                .hasSize(2);
    }

    @Test
    @DisplayName("when not all data is correct")
    void test2() {
        Mockito.when(carsDataJsonDeserializer.fromJson(ArgumentMatchers.anyString()))
                .thenReturn(new CarsData(List.of(
                        new CarData(
                                "audi",
                                "A",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")
                        ),
                        BMW_CAR_DATA
                )));

        assertThat(fileToCarsConverter.convert("cars.json"))
                .hasSize(1)
                .containsOnly(BMW_CAR_DATA.toCar());
    }
}
