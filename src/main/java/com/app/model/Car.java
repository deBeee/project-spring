package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class Car {
    private final String make;
    private final String model;
    private final int speed;
    private final Color color;
    private final BigDecimal price;
    private final List<String> equipment;
}
