package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.repository.SpecialDayRepository;
import com.example.mealplanadmin.service.impl.DateServiceImpl;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DateServiceImplTest {
    private final SpecialDayRepository specialDayRepository = Mockito.mock(SpecialDayRepository.class);

    private final DateService dateService = new DateServiceImpl(
            List.of(LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-15")),
            Set.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"),
            specialDayRepository);

    /*
    Being the current date the 29 of April (Saturday), the first working day should be 2 of May (Tuesday), because the
    1st of May (Monday) is a holiday.
     */
    @Test
    public void firstWorkingDayTest() {
        var date = LocalDate.of(2023, Month.APRIL, 29);
        var expectedDate = LocalDate.of(2023, Month.MAY, 2);
        assertThat(dateService.firstWorkingDay(date)).isEqualTo(expectedDate);
    }

    /*
        The plan is for 10 days, 4 meals per day.
        It starts on May 12.
        There is one special day on May 19 with only 2 meals.
        There is also a holiday during the period (May 15)
     */
    @Test
    public void calculateEndDateTest() {
        var totalDays = 10;
        var mealsPerDay = 4;
        var date = LocalDate.of(2023, Month.MAY, 12);
        var specialDate = LocalDate.of(2023, Month.MAY, 19);
        var specialDay = new SpecialDay(1L, specialDate, 2);
        var expectedEndDate = LocalDate.of(2023, Month.MAY, 29);
        when(specialDayRepository.findByDateGreaterThanEqualOrderByDateAsc(date)).thenReturn(List.of(specialDay));
        assertThat(dateService.calculateEndDate(date, totalDays, mealsPerDay)).isEqualTo(expectedEndDate);
    }

    @Test
    public void calculateEndDateWithoutSpecialDays() {
        var totalDays = 2;
        var mealsPerDay = 2;
        var date = LocalDate.of(2023, Month.MAY, 16);
        var expectedEndDate = LocalDate.of(2023, Month.MAY, 17);
        when(specialDayRepository.findByDateGreaterThanEqualOrderByDateAsc(date)).thenReturn(List.of());
        assertThat(dateService.calculateEndDate(date, totalDays, mealsPerDay)).isEqualTo(expectedEndDate);
    }
}
