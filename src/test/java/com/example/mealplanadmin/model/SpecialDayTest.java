package com.example.mealplanadmin.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialDayTest {
    @Test
    void constructorTest() {
        var specialDayEntity = new SpecialDayEntity(LocalDate.now(), 3);
        specialDayEntity.setId(1L);
        var specialDay = new SpecialDay(specialDayEntity);
        assertThat(specialDay.id()).isEqualTo(specialDayEntity.getId());
        assertThat(specialDay.date()).isEqualTo(specialDayEntity.getDate());
        assertThat(specialDay.meals()).isEqualTo(specialDayEntity.getMeals());
    }
}
