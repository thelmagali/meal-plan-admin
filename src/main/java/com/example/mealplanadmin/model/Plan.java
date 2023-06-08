package com.example.mealplanadmin.model;

import java.time.LocalDate;

public record Plan(Long id, LocalDate startDate, Integer totalDays, Integer mealsPerDay) {

    public Plan(LocalDate startDate, Integer totalDays, Integer mealsPerDay) {
        this(null, startDate, totalDays, mealsPerDay);
    }

    public static Plan fromEntity(PlanEntity planEntity) {
        return new Plan(planEntity.getId(), planEntity.getStartDate(), planEntity.totalDays, planEntity.mealsPerDay);
    }

    public PlanEntity toEntity() {
        return new PlanEntity(startDate, totalDays, mealsPerDay);
    }
}
