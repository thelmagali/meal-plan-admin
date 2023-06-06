package com.example.mealplanadmin.controller;

import com.example.mealplanadmin.model.CreatePlanDTO;
import com.example.mealplanadmin.model.PlanDTO;
import com.example.mealplanadmin.service.PlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("plans")
public class PlanController {

    final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public PlanDTO createPlan(@RequestBody CreatePlanDTO plan) {
        return planService.create(plan);
    }
}
