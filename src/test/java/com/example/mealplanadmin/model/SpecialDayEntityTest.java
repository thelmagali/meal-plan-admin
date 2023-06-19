package com.example.mealplanadmin.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SpecialDayEntityTest {
    @Test
    void emptyConstructorAndAccessors() {
        var specialDay = new SpecialDayEntity();
        assert (specialDay.getMeals() == null);
        assert (specialDay.getDate() == null);
        assert (specialDay.getId() == null);
        var date = LocalDate.now();
        var meals = 4;
        var id = 3L;
        specialDay.setDate(date);
        specialDay.setMeals(meals);
        specialDay.setId(id);
        assert (specialDay.getDate() == date);
        assert (specialDay.getMeals() == meals);
        assert (specialDay.getId() == id);
    }

    @Test
    void constructor() {
        var date = LocalDate.now();
        var mealsPerDay = 4;
        var plan = new SpecialDayEntity(date, mealsPerDay);
        assert (plan.getMeals() == mealsPerDay);
        assert (plan.getDate().equals(date));
        assert (plan.getId() == null);
    }
}
