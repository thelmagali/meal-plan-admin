package com.example.mealplanadmin.model;

import java.time.LocalDate;
import java.util.Optional;

public record PlanDTO(LocalDate startDate, Integer totalDays, Integer mealsPerDay, Optional<LocalDate> endDate) {
}
