package com.app.validation;

import com.app.config.AppTestBeansConfig;
import com.app.data.model.CarData;
import com.app.model.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class ValidatorTest {
    @Autowired
    private Validator<CarData> validator;

    @Test
    @DisplayName("When validation is ok")
    void test1() {
        assertThat(Validator.validate(AUDI_1_CAR_DATA, validator)).isTrue();
    }

    @Test
    @DisplayName("When validation is not ok")
    void test2() {
        var carData = new CarData(
                "AUDi",
                "A",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("A", "B")
        );
        assertThat(Validator.validate(carData, validator)).isFalse();
    }
}

