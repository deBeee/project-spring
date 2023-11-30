package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import com.app.util.MinMax;
import com.app.util.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final List<Car> cars;

    /**
     * Sorts a list of Car objects based on the provided criterion.
     * @param carComparator The criterion for sorting Car objects. It's an object that implements the
     * {@link Comparator<Car>} interface, which defines how two Car objects are compared to each other.
     * @return A list of Car objects sorted based on the given criterion.
     * @throws IllegalArgumentException if car comparator is null.
     */
    @Override
    public List<Car> sort(Comparator<Car> carComparator) {
        if(carComparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }
        return cars
                .stream()
                .sorted(carComparator)
                .toList();
    }

    /**
     * Retrieves a list of Car objects with speeds within the range specified by the given arguments.
     * @param speedMin The minimum speed (inclusive) of the cars to be included in the returned list.
     * @param speedMax The maximum speed (inclusive) of the cars to be included in the returned list.
     * @return A list of Car objects with speeds falling between the provided minimum and maximum values.
     * @throws IllegalArgumentException if speed range is not correct.
     */
    @Override
    public List<Car> findAllBySpeedBetween(int speedMin, int speedMax) {
        if (speedMin > speedMax) {
            throw new IllegalArgumentException("Speed range is not correct");
        }

        return cars
                .stream()
                .filter(car -> car.hasSpeedBetween(speedMin, speedMax))
                .toList();
    }

    /**
     * Finds and returns a list of {@code Car} objects that match the specified
     * criterion.
     * @param criterion The predicate used to test each {@code Car} object.
     * Only cars that satisfy this criterion will be included in the returned list.
     * @return A list of {@code Car} objects that match the specified criterion.
     * If no cars meet the criterion, an empty list is returned.
     */
    @Override
    public List<Car> findAllBy(Predicate<Car> criterion) {
        return cars
                .stream()
                .filter(criterion)
                .toList();
    }

    /**
     * Groups a collection of {@code Car} objects based on the specified classifier.
     * @param classifier a Function interface that takes a {@code Car} object and returns
     * a value of type {@code T} that will be used as a key in the resulting map.
     * @return A map with keys of type {@code T} and values as lists of {@code Car} objects that
     * have been grouped by the classifier.
     * @param <T> the type of the key in the resulting map, determined by the classifier's output.
     */
    @Override
    public <T> Map<T, List<Car>> groupBy(Function<Car, T> classifier) {
        return cars
                .stream()
                .collect(Collectors.groupingBy(classifier));
    }

    /**
     * Counts the number of {@code Car} objects based on the specified classifier.
     * @param classifier a Function interface that takes a {@code Car} object and returns
     * a value of type {@code T} that will be used as a key in the resulting map.
     * @return A map with keys of type {@code T} and values representing the count of {@code Car} objects
     * that match each key.
     * @param <T> the type of the key in the resulting map, determined by the classifier's output.
     */
    @Override
    public <T> Map<T, Long> countBy(Function<Car, T> classifier) {
        return cars
                .stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()));
    }

    /**
     * Groups the cars by a specified criteria and then finds the minimum and maximum values
     * for each group based on another criteria.
     * @param groupingFn a function that produces the key used to group cars.
     * @param carComparator a comparator based on which min and max are determined.
     * @return a map where keys are produced by the grouping function and values are MinMax objects
     * containing the minimum and maximum values based on the comparator for each group.
     * @param <T> the type of the grouping key.
     * @see MinMax
     * @throws IllegalArgumentException if any of the provided functions or comparator is null.
     */
    @Override
    public <T> Map<T, MinMax<Car>> groupAndFindMinMaxByCriteria(Function<Car, T> groupingFn, Comparator<Car> carComparator) {
        if(groupingFn == null) {
            throw new IllegalArgumentException("Grouping function is null");
        }

        if(carComparator == null) {
            throw new IllegalArgumentException("Car comparator is null");
        }

        return cars
                .stream()
                .collect(groupingBy(
                        groupingFn,
                                collectingAndThen(
                                        toList(),
                                        groupedCars -> {
                                            Car minCar = groupedCars
                                                    .stream()
                                                    .min(carComparator)
                                                    .orElseThrow();
                                            Car maxCar = groupedCars
                                                    .stream()
                                                    .max(carComparator)
                                                    .orElseThrow();
                                            return new MinMax<>(minCar, maxCar);
                                        }
                                )
                ));
    }

    /**
     * Groups the cars by a specified criteria and then finds the minimum and maximum values
     * for each group based on another criteria.
     * @param groupingFn a function that produces the key used to group cars.
     * @param minMaxGroupingFn a function that produces the key used to group cars for each group.
     * @param minMaxComparator a comparator based on which min and max are determined.
     * @return a map where keys are produced by the grouping function and values are MinMax objects
     * containing the minimum and maximum values based on the comparator for each group.
     * @param <T> the type of the grouping key.
     * @see MinMax
     * @throws IllegalArgumentException if any of the provided functions or comparator is null.
     */
    @Override
    public <T, U> Map<T, MinMax<List<Car>>> groupAndFindMinMaxByCriteria(
            Function<Car, T> groupingFn, Function<Car, U> minMaxGroupingFn, Comparator<U> minMaxComparator) {
        if(groupingFn == null) {
            throw new IllegalArgumentException("Grouping function is null");
        }

        if(minMaxGroupingFn == null) {
            throw new IllegalArgumentException("Min max grouping function is null");
        }

        if(minMaxComparator == null){
            throw new IllegalArgumentException("Min max comparator is null");
        }

        return cars
                .stream()
                .collect(groupingBy(
                        groupingFn,
                        collectingAndThen(
                                groupingBy(minMaxGroupingFn),
                                groupedByMinMaxFnCars -> {
                                    var minKey = groupedByMinMaxFnCars
                                            .keySet()
                                            .stream()
                                            .min(minMaxComparator)
                                            .orElseThrow();
                                    var maxKey = groupedByMinMaxFnCars
                                            .keySet()
                                            .stream()
                                            .max(minMaxComparator)
                                            .orElseThrow();

                                    return new MinMax<>(
                                            groupedByMinMaxFnCars.get(minKey),
                                            groupedByMinMaxFnCars.get(maxKey)
                                    );
                                }
                        )
                ));
    }

    /**
     * Computes the minimum, maximum, and average values for a list of cars.
     * This method accepts a function that extracts a value of type T from each car.
     * It computes and returns the minimum, maximum, and average of these values.
     * The method is generic and can work with any field of the Car class that implements the Comparable
     * interface. The average is calculated specifically for BigDecimal values.
     *
     * @param extractor a function that extracts the value of type T from a Car object.
     * @param <T> the type of the field to be analyzed. This type must implement the Comparable interface.
     * @return an instance of the Statistics class containing the min, max, and average values.
     */
    @Override
    public <T extends Comparable<T>> Statistics<T> getStatistics(Function<Car, T> extractor) {

        if(extractor == null){
            throw new IllegalArgumentException("Extractor is null");
        }

        T min = cars
                .stream()
                .map(extractor)
                .min(Comparator.naturalOrder())
                .orElse(null);

        T max = cars
                .stream()
                .map(extractor)
                .max(Comparator.naturalOrder())
                .orElse(null);

        BigDecimal avg = cars
                .stream()
                .map(extractor)
                .filter(v -> v instanceof Number)
                .map(v -> {
                    if(v instanceof BigDecimal vv){
                        return vv;
                    }
                    return new BigDecimal(v.toString());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(cars.size()), RoundingMode.HALF_UP);

        return new Statistics<>(min, max, avg);
    }
}
