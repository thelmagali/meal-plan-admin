package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.CreatePlanDTO;
import com.example.mealplanadmin.model.PlanDTO;

public interface PlanService {

    PlanDTO create(CreatePlanDTO createPlanDTO);
}
