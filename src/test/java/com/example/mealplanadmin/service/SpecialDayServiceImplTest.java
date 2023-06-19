package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.SpecialDayDTO;
import com.example.mealplanadmin.repository.SpecialDayRepository;
import com.example.mealplanadmin.service.impl.SpecialDayServiceImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.doNothing;

public class SpecialDayServiceImplTest {

    private final SpecialDayRepository specialDayRepository = Mockito.mock(SpecialDayRepository.class);

    private final SpecialDayService specialDayService = new SpecialDayServiceImpl(specialDayRepository);

    @Test
    public void upsertSpecialDay() {
        var specialDay = new SpecialDayDTO(LocalDate.now(), 1);
        var specialDayToSave = specialDay.toSpecialDay();
        doNothing().when(specialDayRepository).upsert(specialDayToSave);
        assertThatNoException().isThrownBy(() -> specialDayService.upsert(specialDay));
    }
}
