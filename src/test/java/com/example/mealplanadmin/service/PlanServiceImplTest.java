package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.CreatePlanDTO;
import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanDTO;
import com.example.mealplanadmin.repository.PlanRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlanServiceImplTest {

    @Autowired
    private PlanService planService;

    @MockBean
    private PlanRepository planRepository;

    @MockBean
    private DateService dateService;

    @Test
    public void createPlanWithoutCleanup() {
        var planToSave = new Plan(LocalDate.now(), 10, 2);
        var endDate = planToSave.startDate().plusWeeks(2);
        var planSaved = new Plan(1L, planToSave.startDate(), planToSave.totalDays(), planToSave.mealsPerDay());

        when(planRepository.findAllByClosedIsFalse()).thenReturn(List.of());
        when(planRepository.save(planToSave)).thenReturn(planSaved);
        when(dateService.calculateEndDate(planToSave.startDate(), planToSave.totalDays(), planToSave.mealsPerDay())).thenReturn(endDate);

        var createPlanDto = new CreatePlanDTO(planToSave.totalDays(), planToSave.mealsPerDay(), Optional.of(planToSave.startDate()));
        var expectedPlanDto = PlanDTO.fromPlan(planSaved, endDate);
        assertThat(planService.create(createPlanDto)).isEqualTo(expectedPlanDto);
    }

    @Test
    public void createPlanWithCleanup() {
        var startDate = LocalDate.now();
        var planToSave = new Plan(LocalDate.now(), 10, 2);
        var planSaved = new Plan(1L, planToSave.startDate(), planToSave.totalDays(), planToSave.mealsPerDay());
        var endDate = startDate.plusWeeks(2);
        var openPlan = new Plan(2L, startDate.minusDays(30), 10, 4);

        doReturn(List.of(openPlan)).doReturn(List.of()).when(planRepository).findAllByClosedIsFalse();
        when(planRepository.save(planToSave)).thenReturn(planSaved);
        doNothing().when(planRepository).closePlan(planSaved.id());
        when(dateService.calculateEndDate(planToSave.startDate(), planToSave.totalDays(), planToSave.mealsPerDay())).thenReturn(endDate);
        when(dateService.calculateEndDate(openPlan.startDate(), openPlan.totalDays(), openPlan.mealsPerDay())).thenReturn(startDate.minusDays(1));

        var createPlanDto = new CreatePlanDTO(planToSave.totalDays(), planToSave.mealsPerDay(), Optional.of(startDate));
        var expectedPlanDto = PlanDTO.fromPlan(planSaved, endDate);
        assertThat(planService.create(createPlanDto)).isEqualTo(expectedPlanDto);
    }
}
