package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.model.SpecialDayEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SpecialDayRepositoryTest {
    private final SpecialDayRepository specialDayRepository;

    @Autowired
    public SpecialDayRepositoryTest(SpecialDayRepository specialDayRepository) {
        this.specialDayRepository = specialDayRepository;
    }

    @Test
    void create() {
        var specialDayToPersist = new SpecialDay(null, LocalDate.now(), 10);
        specialDayRepository.upsert(specialDayToPersist);
        var allSpecialDays = specialDayRepository.findAll();
        assertThat(allSpecialDays.size()).isEqualTo(1);
        var specialDay = allSpecialDays.get(0);
        assertThat(specialDay.getDate()).isEqualTo(specialDayToPersist.date());
        assertThat(specialDay.getMeals()).isEqualTo(specialDayToPersist.meals());
        assertThat(specialDay.getId()).isNotNull();
    }

    @Test
    void findByDateGreaterThanEqualOrderByDateAsc() {
        var today = LocalDate.now();
        var todaysSpecialDay = new SpecialDayEntity(today, 10);
        var tomorrowsSpecialDay = new SpecialDayEntity(today.plusDays(1), 0);
        var yesterdaysSpecialDay = new SpecialDayEntity(today.minusDays(1), 1);
        var beforeYesterdaySpecialDay = new SpecialDayEntity(today.minusDays(2), 2);
        var specialDays = List.of(todaysSpecialDay, tomorrowsSpecialDay, yesterdaysSpecialDay, beforeYesterdaySpecialDay);
        specialDayRepository.saveAll(specialDays);
        var allSpecialDays = specialDayRepository.findByDateGreaterThanEqualOrderByDateAsc(today).stream()
                .map(this::specialDayToTuple);
        var expectedSpecialDays = Stream.of(todaysSpecialDay, tomorrowsSpecialDay)
                .map(SpecialDay::new)
                .map(this::specialDayToTuple)
                .toList();
        assertThat(allSpecialDays).hasSameElementsAs(expectedSpecialDays);
    }

    private Tuple specialDayToTuple(SpecialDay specialDay) {
        return new Tuple(specialDay.date(), specialDay.meals());
    }
}
