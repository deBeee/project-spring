package com.app.converter.car.impl;

import com.app.converter.car.FileToCarsConverter;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.model.CarData;
import com.app.data.model.CarsData;
import com.app.model.Car;
import com.app.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JsonFileToCarsConverterImpl implements FileToCarsConverter {

    private final JsonDeserializer<CarsData> carsDataJsonDeserializer;
    private final Validator<CarData> carDataValidator;

    @Override
    public List<Car> convert(String filename) {
        return carsDataJsonDeserializer
                .fromJson(filename)
                .cars()
                .stream()
                .filter(carData -> Validator.validate(carData, carDataValidator))
                .map(CarData::toCar)
                .toList();
    }
}
