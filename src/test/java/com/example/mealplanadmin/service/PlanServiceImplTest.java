package com.example.mealplanadmin.service;

import com.example.mealplanadmin.mapper.PlanEntityMapper;
import com.example.mealplanadmin.model.CreatePlanDTO;
import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.repository.PlanRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlanServiceImplTest {

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanEntityMapper planEntityMapper;

    @MockBean
    private PlanRepository planRepository;

    @MockBean
    private DateService dateService;

    @Test
    public void createPlanWithoutCleanup() {
        var totalDays = 10;
        var mealsPerDay = 2;
        var startDate = LocalDate.now();
        var planEntity = new Plan(startDate, totalDays, mealsPerDay);
        var endDate = startDate.plusWeeks(2);

        when(planRepository.findAllByClosedIsFalse()).thenReturn(List.of());
        when(planRepository.save(planEntity)).thenReturn(planEntity);
        when(dateService.calculateEndDate(planEntity)).thenReturn(endDate);

        var createPlanDto = new CreatePlanDTO(totalDays, mealsPerDay, Optional.of(startDate));
        var expectedPlan = planEntityMapper.toDTO(planEntity, endDate);
        assertThat(planService.create(createPlanDto)).isEqualTo(expectedPlan);
    }

    @Test
    public void createPlanWithCleanup() {
        var totalDays = 10;
        var mealsPerDay = 2;
        var startDate = LocalDate.now();
        var planEntity = new Plan(startDate, totalDays, mealsPerDay);
        var endDate = startDate.plusWeeks(2);
        var openPlan = new Plan(startDate.minusDays(30), 10, 4);

        doReturn(List.of(openPlan)).doReturn(List.of()).when(planRepository).findAllByClosedIsFalse();
        when(planRepository.save(planEntity)).thenReturn(planEntity);
        doNothing().when(planRepository).closePlan(any());
        when(dateService.calculateEndDate(planEntity)).thenReturn(endDate);
        when(dateService.calculateEndDate(openPlan)).thenReturn(startDate.minusDays(1));

        var createPlanDto = new CreatePlanDTO(totalDays, mealsPerDay, Optional.of(startDate));
        var expectedPlan = planEntityMapper.toDTO(planEntity, endDate);
        assertThat(planService.create(createPlanDto)).isEqualTo(expectedPlan);
    }
}
