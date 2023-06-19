package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.model.SpecialDayEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SpecialDayRepository extends JpaRepository<SpecialDayEntity, Long> {
    @Query("select new com.example.mealplanadmin.model.SpecialDay(sd) from SpecialDayEntity sd where sd.date >= :date order by sd.date asc ")
    List<SpecialDay> findByDateGreaterThanEqualOrderByDateAsc(LocalDate date);

    @Modifying
    @Transactional
    default void upsert(SpecialDay specialDay) {
        save(specialDay.toEntity());
    }
}
