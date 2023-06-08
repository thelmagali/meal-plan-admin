package com.example.mealplanadmin.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

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
        var date = LocalDate.now();
        var mealsPerDay = 4;
        var plan = new SpecialDay(date, mealsPerDay);
        assert (plan.getMeals() == mealsPerDay);
        assert (plan.getDate().equals(date));
        assert (plan.getId() == null);
    }
}
