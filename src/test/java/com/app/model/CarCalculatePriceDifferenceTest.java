package com.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.app.Cars.MAZDA_1_CAR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarCalculatePriceDifferenceTest {
    @Test
    @DisplayName("when car's price is lower than other price")
    void test1() {
        assertThat(MAZDA_1_CAR.calculatePriceDifference(BigDecimal.TEN))
                .isEqualTo(BigDecimal.valueOf(8));
    }

    @Test
    @DisplayName("when car's price is greater than other price")
    void test2() {
        assertThat(MAZDA_1_CAR.calculatePriceDifference(BigDecimal.ONE))
                .isEqualTo(BigDecimal.ONE);
    }
}
