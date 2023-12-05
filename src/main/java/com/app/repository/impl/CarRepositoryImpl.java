package com.app.repository.impl;

import com.app.model.Car;
import com.app.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {
    @Override
    public List<Car> getCars() {
        return null;
    }
}
