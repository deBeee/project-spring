package com.app.util;

import com.app.model.Color;

import java.math.BigDecimal;
import java.util.List;

public record CarCriteria(
        String requiredMakeRegex,
        String requiredModelRegex,
        int speedMin,
        int speedMax,
        BigDecimal priceMin,
        BigDecimal priceMax,
        List<String> requiredEquipment,
        Color color
) { }
