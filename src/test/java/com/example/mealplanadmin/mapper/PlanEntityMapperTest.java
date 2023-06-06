package com.example.mealplanadmin.mapper;

import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanDTO;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlanEntityMapperTest {

    @Autowired
    PlanEntityMapper planEntityMapper;
    LocalDate startDate = LocalDate.now();

    @Test
    public void toDTOTest() {
        var mealsPerDay = 2;
        var totalDays = 15;
        var inputPlan = new Plan(startDate, totalDays, mealsPerDay);
        var expectedPlan = new PlanDTO(startDate, totalDays, mealsPerDay, Optional.empty());
        assertThat(planEntityMapper.toDTO(inputPlan)).isEqualTo(expectedPlan);
    }

    @Test
    public void toDTOTWithEndDateTest() {
        var mealsPerDay = 2;
        var totalDays = 10;
        var endDate = startDate.plusMonths(1);
        var inputPlan = new Plan(startDate, totalDays, mealsPerDay);
        var expectedPlan = new PlanDTO(startDate, totalDays, mealsPerDay, Optional.of(endDate));
        assertThat(planEntityMapper.toDTO(inputPlan, endDate)).isEqualTo(expectedPlan);
    }

    @Test
    public void toPlan() {
        var mealsPerDay = 1;
        var totalDays = 7;
        var inputPlanDto = new PlanDTO(startDate, totalDays, mealsPerDay, Optional.empty());
        var expectedPlan = new Plan(startDate, totalDays, mealsPerDay);
        assertThat(planEntityMapper.toPlan(inputPlanDto)).isEqualTo(expectedPlan);
    }
}
