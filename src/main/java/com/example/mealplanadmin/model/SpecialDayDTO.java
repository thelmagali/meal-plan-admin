package com.example.mealplanadmin.model;

import java.time.LocalDate;

public record SpecialDayDTO(LocalDate date, Integer meals) {
    public SpecialDay toSpecialDay() {
        return new SpecialDay(null, date, meals);
    }
}
