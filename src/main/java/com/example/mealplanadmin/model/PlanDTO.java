package com.example.mealplanadmin.model;

import java.time.LocalDate;

public record PlanDTO(LocalDate startDate, Integer totalDays, Integer mealsPerDay, LocalDate endDate) {
    public static PlanDTO fromPlan(Plan plan, LocalDate endDate) {
        return new PlanDTO(plan.startDate(), plan.totalDays(), plan.mealsPerDay(), endDate);
    }
}
