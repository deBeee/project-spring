package com.app;

import com.app.data.model.CarData;
import com.app.model.Car;
import com.app.model.Color;

import java.math.BigDecimal;
import java.util.List;

public interface Cars {
    CarData AUDI_1_CAR_DATA = new CarData(
            "AUDI",
            "A",
            200,
            Color.BLACK,
            BigDecimal.ONE,
            List.of("A", "B")
    );

    Car AUDI_1_CAR = AUDI_1_CAR_DATA.toCar();

    CarData AUDI_2_CAR_DATA = new CarData(
            "AUDI",
            "AA",
            210,
            Color.BLACK,
            BigDecimal.TEN,
            List.of("A", "C")
    );

    Car AUDI_2_CAR = AUDI_2_CAR_DATA.toCar();

    CarData AUDI_3_CAR_DATA = new CarData(
            "AUDI",
            "AAA",
            210,
            Color.BLACK,
            BigDecimal.TEN,
            List.of("A", "C")
    );

    Car AUDI_3_CAR = AUDI_2_CAR_DATA.toCar();

    CarData BMW_CAR_DATA = new CarData(
            "BMW",
            "B",
            250,
            Color.WHITE,
            BigDecimal.TEN,
            List.of("A", "B", "C")
    );

    Car BMW_CAR = BMW_CAR_DATA.toCar();

    CarData MAZDA_1_CAR_DATA = new CarData(
            "MAZDA",
            "M",
            190,
            Color.BLUE,
            BigDecimal.TWO,
            List.of("B", "C")
    );

    Car MAZDA_1_CAR = MAZDA_1_CAR_DATA.toCar();

    CarData MAZDA_2_CAR_DATA = new CarData(
            "MAZDA",
            "MM",
            205,
            Color.BLACK,
            BigDecimal.ONE,
            List.of("C", "D")
    );

    Car MAZDA_2_CAR = MAZDA_2_CAR_DATA.toCar();
}
