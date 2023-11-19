package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final List<Car> cars;

    /**
     * Sorts a list of Car objects based on the provided criterion.
     * @param carComparator The criterion for sorting Car objects. It's an object that implements the
     * {@link Comparator<Car>} interface, which defines how two Car objects are compared to each other.
     * @return A list of Car objects sorted based on the given criterion.
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
     * @param <T> The type of the input to the predicate, which should match the
     * type of elements this method operates on.
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

}
