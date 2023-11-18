package com.app.service;

import com.app.model.Car;

import java.util.Comparator;
import java.util.List;

public interface CarService {
    List<Car> sort(Comparator<Car> carComparator);
    List<Car> findAllBySpeedBetween(int speedMin, int speedMax);
}
