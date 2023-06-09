package com.example.mealplanadmin.model;

import java.time.LocalDate;

public record SpecialDay(Long id, LocalDate date, Integer meals) {
    public SpecialDay(SpecialDayEntity entity) {
        this(entity.getId(), entity.getDate(), entity.getMeals());
    }
}
