package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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
}
