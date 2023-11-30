package com.app.util;

import java.math.BigDecimal;

public record Statistics<T>(T min, T max, BigDecimal avg) { }
