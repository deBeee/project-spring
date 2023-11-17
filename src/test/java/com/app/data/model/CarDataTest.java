package com.app.data.model;

import com.app.model.Car;
import com.app.model.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarDataTest {
    @Test
    @DisplayName("when conversion from carData to Car works")
    void test1() {
        var carData = new CarData(
                "AUDI",
                "Q8",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("A", "B")
        );

        var expectedCar = new Car(
                "AUDI",
                "Q8",
                200,
                BigDecimal.ONE,
                Color.BLACK,
                List.of("A", "B")
        );

        assertThat(carData.toCar()).isEqualTo(expectedCar);
    }
}
