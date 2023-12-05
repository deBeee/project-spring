package com.app.service.impl;

import com.app.service.HtmlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static com.app.Cars.AUDI_1_CAR;
import static com.app.Cars.AUDI_2_CAR;
import static org.assertj.core.api.Assertions.assertThat;

public class HtmlServiceImplTest {
    private static final HtmlService htmlService = new HtmlServiceImpl();

    @Test
    @DisplayName("when object is converted to html correctly")
    void test1() {
        var expectedHtml
                = "<div><h2>Car</h2><p>Car: [AUDI, A, 200, 1, BLACK, A, B]</p></div>";
        assertThat(htmlService.toHtml("Car", AUDI_1_CAR))
                .isEqualTo(expectedHtml);
    }

    @Test
    @DisplayName("when list is converted to html correctly")
    void test2() {
        var expectedHtml
                = "<div><h2>Cars</h2><ol><li>Car: [AUDI, A, 200, 1, BLACK, A, B]</li><li>Car: [AUDI, AA, 210, 10, BLACK, A, C]</li></ol></div>";
        assertThat(htmlService.toHtmlMany("Cars", List.of(AUDI_1_CAR, AUDI_2_CAR)))
                .isEqualTo(expectedHtml);
    }

    @Test
    @DisplayName("when map is converted to html correctly")
    void test3() {
        var expectedHtml
                = "<div><h2>Cars</h2><ol><li>A, [Car: [AUDI, A, 200, 1, BLACK, A, B], Car: [AUDI, AA, 210, 10, BLACK, A, C]]</li><li>B, [Car: [AUDI, A, 200, 1, BLACK, A, B]]</li></ol></div>";
        var carMap = new LinkedHashMap<>();
        carMap.put("A", List.of(AUDI_1_CAR, AUDI_2_CAR));
        carMap.put("B", List.of(AUDI_1_CAR));

        assertThat(htmlService.toHtmlPairs("Cars", carMap)).isEqualTo(expectedHtml);
    }
}
