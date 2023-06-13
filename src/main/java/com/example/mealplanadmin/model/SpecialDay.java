package com.example.mealplanadmin.model;

import java.time.LocalDate;

public record SpecialDay(Long id, LocalDate date, Integer meals) {
    public SpecialDay(SpecialDayEntity entity) {
        this(entity.getId(), entity.getDate(), entity.getMeals());
    }

    public SpecialDayEntity toEntity() {
        var entity = new SpecialDayEntity(date, meals);
        entity.setId(id);
        return entity;
    }
}
