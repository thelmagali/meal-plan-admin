package com.example.mealplanadmin.service;

import java.time.LocalDate;

public interface DateService {
    LocalDate firstWorkingDay(LocalDate date);

    LocalDate calculateEndDate(LocalDate startDate, Integer totalDays, Integer mealsPerDay);
}
