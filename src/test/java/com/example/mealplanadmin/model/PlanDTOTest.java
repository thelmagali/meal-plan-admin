package com.example.mealplanadmin.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlanDTOTest {
    @Test
    public void fromPlan(){
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(1);
        var plan = new Plan(1L, LocalDate.now(), 10, 3);
        var planDto = PlanDTO.fromPlan(plan, endDate);
        assertThat(planDto).isEqualTo(new PlanDTO(plan.startDate(), plan.totalDays(), plan.mealsPerDay(), endDate));
    }
}
