package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.Plan;
import java.time.LocalDate;

public interface DateService {
    LocalDate firstWorkingDay(LocalDate date);

    LocalDate calculateEndDate(Plan plan);
}
