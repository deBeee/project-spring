package com.app.model;

import com.app.model.Car;

import java.util.Comparator;
import java.util.function.Function;

public interface Comparators {

    /*Comparator<Car> byMakeComparator
            = Comparator.comparing(car -> car.make);
    Comparator<Car> ByMakeComparatorDesc
            = Comparator.comparing(car -> car.make, Comparator.reverseOrder());
    Comparator<Car> bySpeedComparator
            = Comparator.comparing(car -> car.speed);
    Comparator<Car> BySpeedComparatorDesc
            = Comparator.comparing(car -> car.speed, Comparator.reverseOrder());
    Comparator<Car> byPriceComparator
            = Comparator.comparing(car -> car.price);
    Comparator<Car> byPriceComparatorDesc
            = Comparator.comparing(car -> car.price, Comparator.reverseOrder());*/

    private static <T,U extends Comparable<U>> Comparator<T> generalComparator(
            Function<T,U> keyExtractor, boolean isDescending
    ) {
        return isDescending ?
                Comparator.comparing(keyExtractor, Comparator.reverseOrder()) :
                Comparator.comparing(keyExtractor);
    }

    Comparator<Car> byMakeComparator = generalComparator(car -> car.make, false);
    Comparator<Car> byMakeComparatorDesc = generalComparator(car -> car.make, true);
    Comparator<Car> bySpeedComparator = generalComparator(car -> car.speed, false);
    Comparator<Car> bySpeedComparatorDesc = generalComparator(car -> car.speed, true);
    Comparator<Car> byPriceComparator = generalComparator(car -> car.price, false);
    Comparator<Car> byPriceComparatorDesc = generalComparator(car -> car.price, true);
}
