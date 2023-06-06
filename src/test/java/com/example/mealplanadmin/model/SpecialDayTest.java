package com.example.mealplanadmin.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpecialDayTest {
    @Test
    void emptyConstructorAndAccessors() {
        var specialDay = new SpecialDay();
        assert (specialDay.getMeals() == null);
        assert (specialDay.getDate() == null);
        assert (specialDay.getId() == null);
        var date = LocalDate.now();
        var meals = 4;
        specialDay.setDate(date);
        specialDay.setMeals(meals);
        assert (specialDay.getDate() == date);
        assert (specialDay.getMeals() == meals);
    }

    @Test
    void constructor() {
        var startDate = LocalDate.now();
        var mealsPerDay = 4;
        var totalDays = 30;
        var plan = new Plan(startDate, totalDays, mealsPerDay);
        assert (!plan.getClosed());
        assert (plan.getTotalDays() == totalDays);
        assert (plan.getStartDate().equals(startDate));
        assert (plan.getMealsPerDay() == mealsPerDay);
        assert (plan.getId() == null);
    }
}
