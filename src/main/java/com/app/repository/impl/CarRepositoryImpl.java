package com.app.repository.impl;

import com.app.converter.car.FileToCarsConverter;
import com.app.model.Car;
import com.app.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

    @Value("${cars.filename}")
    String filename;

    private final FileToCarsConverter fileToCarsConverter;

    private List<Car> cars;
    @PostConstruct
    void init() {
        cars = fileToCarsConverter.convert(filename);
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }
}


