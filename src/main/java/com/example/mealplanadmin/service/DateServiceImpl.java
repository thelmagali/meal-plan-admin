package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.repository.SpecialDayRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {

    private final List<LocalDate> holidays;

    private final Set<DayOfWeek> workingDays;

    private final SpecialDayRepository specialDayRepository;


    @Autowired
    public DateServiceImpl(@Value("${holidays}") List<LocalDate> holidays,
                           @Value("${working.days}") Set<String> workingDaysString,
                           SpecialDayRepository specialDayRepository) {
        this.holidays = holidays;
        this.workingDays = workingDaysString.stream().map(DayOfWeek::valueOf).collect(Collectors.toSet());
        this.specialDayRepository = specialDayRepository;
    }

    @Override
    public LocalDate firstWorkingDay(final LocalDate date) {
        if (!isWorkingDay(date)) {
            return firstWorkingDay(date.plusDays(1));
        }
        return date;
    }

    @Override
    public LocalDate calculateEndDate(Plan plan) {
        var remainingMeals = plan.getTotalDays() * plan.getMealsPerDay();
        var currentDate = plan.getStartDate().minusDays(1);
        var futureSpecialDays = specialDayRepository.findByDateGreaterThanEqualOrderByDateAsc(plan.getStartDate());
        while (remainingMeals > 0) {
            currentDate = currentDate.plusDays(1);
            remainingMeals = remainingMeals - getMealsForDate(currentDate, plan.getMealsPerDay(), futureSpecialDays);
            futureSpecialDays = dropPreviousSpecialDates(currentDate, futureSpecialDays);
        }
        return currentDate;
    }

    // Deletes all the special dates that are before the current date.
    // This is to avoid traversing already visited dates multiple times
    private List<SpecialDay> dropPreviousSpecialDates(final LocalDate currentDate, List<SpecialDay> futureSpecialDates) {
        return futureSpecialDates.stream().dropWhile(sp -> sp.getDate().isBefore(currentDate)).toList();
    }

    // If `date` is a special date, it should be the first element of `futureSpecialDays`, and we return the meals of that day.
    // Otherwise, return the default meals per day
    private int getMealsForDate(LocalDate date, Integer mealsPerDay, List<SpecialDay> futureSpecialDays) {
        if (isWorkingDay(date)) {
            return futureSpecialDays
                    .stream()
                    .findFirst()
                    .filter(sd -> sd.getDate().equals(date))
                    .map(SpecialDay::getMeals)
                    .orElse(mealsPerDay);
        }
        return 0;
    }

    private boolean isWorkingDay(LocalDate date) {
        return workingDays.contains(date.getDayOfWeek()) && !isHoliday(date);
    }

    private boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
