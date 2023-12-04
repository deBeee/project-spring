package com.app.model;

import com.app.util.CarCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class Car {
    final String make;
    final String model;
    final int speed;
    final BigDecimal price;
    final Color color;
    final List<String> equipment;

    /**
     * Checks if the car's speed is within the specified range.
     * This method evaluates whether the speed of this car is between
     * the minimum (inclusive) and maximum (inclusive) speed values provided.
     *
     * @param speedMin The minimum speed in the range.
     * @param speedMax The maximum speed in the range.
     * @return true if the car's speed is within the range, false otherwise.
     */
    public boolean hasSpeedBetween(int speedMin, int speedMax) {
        return speedMin <= speed && speed <= speedMax;
    }

    /**
     * Creates a new Car instance with sorted equipment.
     * This method sorts the equipment of the car based on the provided Comparator.
     * It returns a new Car instance with the same properties as this car, but with
     * the equipment sorted as per the equipmentComparator.
     *
     * @param equipmentComparator The Comparator to be used for sorting the equipment.
     * @return A new Car object with sorted equipment.
     */
    public Car sortEquipment(Comparator<String> equipmentComparator) {
        return Car
                .builder()
                .make(make)
                .model(model)
                .speed(speed)
                .color(color)
                .price(price)
                .equipment(equipment.stream().sorted(equipmentComparator).toList())
                .build();
    }

    /**
     * Calculates the difference in price between this car and another price.
     * This method computes the absolute difference between the price of this car
     * and another price value provided as an argument. The difference is returned
     * as a BigDecimal, ensuring precision in calculation.
     *
     * @param otherPrice The price to be compared with this car's price.
     * @return The absolute difference in price as a BigDecimal.
     */
    public BigDecimal calculatePriceDifference(BigDecimal otherPrice) {
        return this.price.subtract(otherPrice).abs();
    }

    /**
     * Calculates the difference in speed between this car and another speed.
     * This method computes the absolute difference between the speed of this car
     * and another speed value provided as an argument.
     *
     * @param otherSpeed The speed to be compared with this car's speed.
     * @return The absolute difference in speed as int.
     */
    public int calculateSpeedDifference(int otherSpeed) {
        return abs(this.speed - otherSpeed);
    }

    public boolean matchesCriteria(CarCriteria carCriteria) {
        var matchesMake = this.make.matches(carCriteria.requiredMakeRegex());
        var matchesModel = this.model.matches(carCriteria.requiredModelRegex());
        var matchesSpeed = hasSpeedBetween(carCriteria.speedMin(), carCriteria.speedMax());
        var matchesPrice = carCriteria.priceMin().compareTo(this.price) <= 0
                && this.price.compareTo(carCriteria.priceMax()) <= 0;
        var matchesColor = this.color.equals(carCriteria.color());
        var containsEquipment = this.equipment.containsAll(carCriteria.requiredEquipment());
        return matchesMake && matchesModel && matchesSpeed && matchesPrice && matchesColor && containsEquipment;
    }

    @Override
    public String toString() {
        return "Car: [%s, %s, %d, %s, %s, %s]".formatted(
                make,
                model,
                speed,
                price,
                color,
                String.join(", ", equipment)
        );
    }
}

