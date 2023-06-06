package com.example.mealplanadmin.model;

import java.time.LocalDate;
import java.util.Optional;

public record CreatePlanDTO(Integer totalDays, Integer mealsPerDay, Optional<LocalDate> startDate) {
}