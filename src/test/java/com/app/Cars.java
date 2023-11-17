package com.app;

import com.app.data.model.CarData;
import com.app.model.Car;
import com.app.model.Color;

import java.math.BigDecimal;
import java.util.List;

public interface Cars {
    CarData AUDI_CAR_DATA = new CarData(
            "AUDI",
            "A",
            200,
            Color.BLACK,
            BigDecimal.ONE,
            List.of("A", "B")
    );
    Car AUDI_CAR = AUDI_CAR_DATA.toCar();

    CarData BMW_CAR_DATA =  new CarData(
            "BMW",
            "B",
            250,
            Color.WHITE,
            BigDecimal.TEN,
            List.of("A", "B", "C")
    );
    Car BMW_CAR = BMW_CAR_DATA.toCar();
    CarData MAZDA_CAR_DATA =  new CarData(
            "MAZDA",
            "M",
            190,
            Color.BLUE,
            BigDecimal.TWO,
            List.of("B", "C")
    );

    Car MAZDA_CAR = MAZDA_CAR_DATA.toCar();
}
