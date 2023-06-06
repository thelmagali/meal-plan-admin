package com.example.mealplanadmin.mapper;

import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanDTO;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PlanEntityMapper {
    public Plan toPlan(PlanDTO planDTO) {
        return new Plan(planDTO.startDate(), planDTO.totalDays(), planDTO.mealsPerDay());
    }

    public PlanDTO toDTO(Plan plan, LocalDate endDate) {
        return new PlanDTO(plan.getStartDate(), plan.getTotalDays(), plan.getMealsPerDay(), Optional.of(endDate));
    }

    public PlanDTO toDTO(Plan plan) {
        return new PlanDTO(plan.getStartDate(), plan.getTotalDays(), plan.getMealsPerDay(), Optional.empty());
    }
}
