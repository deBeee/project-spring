package com.app.service;

import com.app.model.Car;
import com.app.util.MinMax;
import com.app.util.Statistics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CarService {
    List<Car> sort(Comparator<Car> carComparator);
    List<Car> findAllBySpeedBetween(int speedMin, int speedMax);
    List<Car> findAllBy(Predicate<Car> criterion);

    <T> Map<T, List<Car>> groupBy(Function<Car, T> classifier);
    <T> Map<T, Long> countBy(Function<Car, T> classifier);
    <T> Map<T, MinMax<Car>> groupAndFindMinMaxByCriteria(Function<Car, T> groupingFn, Comparator<Car> carComparator);
    <T, U> Map<T, MinMax<List<Car>>> groupAndFindMinMaxByCriteria(
            Function<Car, T> groupingFn, Function<Car, U> minMaxGroupingFn, Comparator<U> minMaxComparator);
    <T extends Comparable<T>>Statistics<T> getStatistics(Function<Car, T> extractor);
    List<Car> sortEquipment(Comparator<String> equipmentComparator);
    Map<String, List<Car>> groupByComponent(Comparator<List<Car>> carsComparator);
    List<Car> findCarsClosestToCriteria(Comparator<Car> carComparator);
    void sendReport(String to, String subject);
}
