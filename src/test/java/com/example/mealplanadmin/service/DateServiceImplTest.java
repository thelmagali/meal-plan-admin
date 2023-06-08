package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.repository.SpecialDayRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = { "holidays=2023-05-01,2023-05-15","working.days=MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY" })
public class DateServiceImplTest {

    @Autowired
    private DateService dateService;

    @MockBean
    private SpecialDayRepository specialDayRepository;


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
        var specialDay = new SpecialDay(specialDate, 2);
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
