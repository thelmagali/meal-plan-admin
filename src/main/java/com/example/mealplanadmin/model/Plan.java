package com.example.mealplanadmin.model;

import java.time.LocalDate;

public record Plan(Long id, LocalDate startDate, Integer totalDays, Integer mealsPerDay) {

    public Plan(LocalDate startDate, Integer totalDays, Integer mealsPerDay) {
        this(null, startDate, totalDays, mealsPerDay);
    }

    public Plan(PlanEntity planEntity) {
        this(planEntity.getId(), planEntity.getStartDate(), planEntity.getTotalDays(), planEntity.getMealsPerDay());
    }

    public PlanEntity toEntity() {
        return new PlanEntity(startDate, totalDays, mealsPerDay);
    }
}
