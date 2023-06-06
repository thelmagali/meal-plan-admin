package com.example.mealplanadmin.service.impl;

import com.example.mealplanadmin.exception.TooManyPendingPlansException;
import com.example.mealplanadmin.mapper.PlanEntityMapper;
import com.example.mealplanadmin.model.CreatePlanDTO;
import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanDTO;
import com.example.mealplanadmin.repository.PlanRepository;
import com.example.mealplanadmin.service.DateService;
import com.example.mealplanadmin.service.PlanService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    PlanEntityMapper planEntityMapper;

    PlanRepository planRepository;

    DateService dateService;

    @Autowired
    public PlanServiceImpl(PlanEntityMapper planEntityMapper, PlanRepository planRepository, DateService dateService) {
        this.planEntityMapper = planEntityMapper;
        this.planRepository = planRepository;
        this.dateService = dateService;
    }

    @Override
    @Transactional
    public PlanDTO create(CreatePlanDTO createPlanDTO) {
        updatePlansStatus();
        Plan plan = savePlan(createPlanDTO);
        var endDate = dateService.calculateEndDate(plan);
        return planEntityMapper.toDTO(plan, endDate);
    }

    private void updatePlansStatus() {
        var openPlans = planRepository.findAllByClosedIsFalse();
        openPlans.forEach(this::closePlanIfEnded);
    }

    private void closePlanIfEnded(Plan plan) {
        var endDate = dateService.calculateEndDate(plan);
        if (isPastDate(endDate)) {
            planRepository.closePlan(plan.getId());
        }
    }

    private boolean isPastDate(LocalDate date) {
        return !LocalDate.now().isBefore(date); // this support isAfter or equals
    }

    private Plan savePlan(CreatePlanDTO createPlanDTO) {
        var startDate = calculateNewPlanStartDate(createPlanDTO.startDate());
        var planEntity = new Plan(startDate, createPlanDTO.totalDays(), createPlanDTO.mealsPerDay());
        return planRepository.save(planEntity);
    }

    private LocalDate calculateNewPlanStartDate(Optional<LocalDate> optionalStartDate) {
        var activeAndPendingPlans = planRepository.findAllByClosedIsFalse();
        if (activeAndPendingPlans.size() > 1) {
            throw new TooManyPendingPlansException();
        } else if (activeAndPendingPlans.isEmpty()) {
            return getStartDateOrTomorrow(optionalStartDate);
        }
        var lastPlan = activeAndPendingPlans.get(0);
        var endDateFromLast = dateService.calculateEndDate(lastPlan);
        return dateService.firstWorkingDay(endDateFromLast.plusDays(1));
    }

    private LocalDate getStartDateOrTomorrow(Optional<LocalDate> optionalStartDate) {
        return optionalStartDate.orElseGet(() -> LocalDate.now().plusDays(1));
    }
}
