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
    final String make;
    final String model;
    final int speed;
    final BigDecimal price;
    final Color color;
    final List<String> equipment;
}
