package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.SpecialDay;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialDayRepository extends JpaRepository<SpecialDay, Long> {
    List<SpecialDay> findByDateGreaterThanEqualOrderByDateAsc(LocalDate date);

}
