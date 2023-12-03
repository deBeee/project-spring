package com.app.model;

import com.app.Cars;
import com.app.data.model.CarData;
import com.app.util.CarCriteria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.app.Cars.MAZDA_1_CAR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarMatchesCriteriaTest {
    @Test
    @DisplayName("when car matches criteria")
    void test1() {
        CarCriteria carCriteria = new CarCriteria(
                "^M.*",
                ".*M$",
                100,
                200,
                BigDecimal.ONE,
                BigDecimal.TEN,
                List.of("B", "C"),
                Color.BLUE
        );

        assertThat(MAZDA_1_CAR.matchesCriteria(carCriteria)).isTrue();
    }

    @Test
    @DisplayName("when car does not match criteria")
    void test2() {
        CarCriteria carCriteria = new CarCriteria(
                "^M.*",
                ".*A$",
                100,
                200,
                BigDecimal.ONE,
                BigDecimal.TEN,
                List.of("A", "B"),
                Color.BLACK
        );
        assertThat(MAZDA_1_CAR.matchesCriteria(carCriteria)).isFalse();
    }
}
