package com.app.validation.impl;

import com.app.config.AppBeansConfig;
import com.app.config.AppTestBeansConfig;
import com.app.data.model.CarData;
import com.app.model.Car;
import com.app.model.Color;
import com.app.validation.Validator;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class CarDataValidatorTest {
    @Autowired
    private Validator<CarData> validator;

    static Stream<Arguments> validationData() {
        return Stream.of(
                Arguments.of(
                        new CarData(
                                "AUDi",
                                "A",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")
                        ),
                        Map.of("make", "does not match regex: AUDi")
                ),
                Arguments.of(
                        new CarData(
                                "AUDI",
                                "A7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")
                        ),
                        Map.of("model", "does not match regex: A7")
                ),
                Arguments.of(
                        new CarData(
                                "AUDI",
                                "A",
                                -200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")
                        ),
                        Map.of("speed", "must be positive: -200")
                ),
                Arguments.of(
                        new CarData(
                                "AUDI",
                                "A",
                                200,
                                Color.BLACK,
                                BigDecimal.valueOf(-1),
                                List.of("A", "B")
                        ),
                        Map.of("price", "must be positive: -1")
                ),
                Arguments.of(
                        new CarData(
                                "AUDI",
                                "A",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("Aa", "B")
                        ),
                        Map.of("equipment", "not all items match regex: [Aa, B]")
                ),
                Arguments.of(
                        new CarData(
                                "AUDI",
                                null,
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")
                        ),
                        Map.of("model", "does not match regex: null")
                ),
                Arguments.of(
                        new CarData(
                                "AUDi",
                                "Aa",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")
                        ),
                        Map.of(
                                "make", "does not match regex: AUDi",
                                "model", "does not match regex: Aa"
                        )
                )

        );
    }

    @ParameterizedTest
    @MethodSource("validationData")
    @DisplayName("When validation result is not correct")
    void test1(CarData carData, Map<String, String> expectedErrors) {
        assertThat(validator.validate(carData))
                .isEqualTo(expectedErrors);
    }

}
