package com.example.mealplanadmin.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlanTest {
    @Test
    public void constructorWithoutId() {
        var startDate = LocalDate.now();
        var totalDays = 10;
        var mealsPerDay = 5;
        var plan = new Plan(startDate, totalDays, mealsPerDay);
        assertThat(plan.id()).isEqualTo(null);
        assertThat(plan.totalDays()).isEqualTo(totalDays);
        assertThat(plan.mealsPerDay()).isEqualTo(mealsPerDay);
    }

    @Test
    public void toEntity() {
        var startDate = LocalDate.now();
        var totalDays = 10;
        var mealsPerDay = 5;
        var plan = new Plan(startDate, totalDays, mealsPerDay);
        var entity = plan.toEntity();
        assertThat(entity.getStartDate()).isEqualTo(startDate);
        assertThat(entity.getTotalDays()).isEqualTo(totalDays);
        assertThat(entity.getMealsPerDay()).isEqualTo(mealsPerDay);
        assertThat(entity.getId()).isEqualTo(null);
    }

    @Test
    public void fromEntity() {
        var startDate = LocalDate.now();
        var totalDays = 10;
        var mealsPerDay = 5;
        var entity = new PlanEntity(startDate, totalDays, mealsPerDay);
        var plan = new Plan(entity);
        assertThat(plan).isEqualTo(new Plan(startDate, totalDays, mealsPerDay));
    }
}
