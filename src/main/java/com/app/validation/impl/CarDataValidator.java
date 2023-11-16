package com.app.validation.impl;
import com.app.data.model.CarData;
import com.app.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class CarDataValidator implements Validator<CarData> {
    @Value("${validation.regex}")
    private String regex;
    private final Map<String, String> errors = new HashMap<>();
    @Override
    public boolean validate(CarData carData) {
        if(!doesNotMatchRegex(carData.model(), regex)) {
            errors.put("model", "does not match regex: " + carData.model());
        }

        if(!doesNotMatchRegex(carData.make(), regex)) {
            errors.put("make", "does not match regex: " + carData.make());
        }

        if(carData.equipment().stream().anyMatch(eq -> !doesNotMatchRegex(eq, regex))) {
            errors.put("equipment", "not all items match regex" + carData.equipment());
        }

        if(carData.speed() <= 0) {
            errors.put("speed", "must be positive: " + carData.speed());
        }

        if(carData.price().compareTo(BigDecimal.ZERO) <= 0) {
            errors.put("price", "must be positive: " + carData.price());
        }
        // TODO Use loggers to print validation errors
        return errors.isEmpty();
    }

    private static boolean doesNotMatchRegex(String text, String regex){
        return text == null || !text.matches(regex);
    }
}
