package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Transactional
    @Modifying
    @Query("update Plan p set p.closed = true where p.id = :id")
    void closePlan(Long id);
    List<Plan> findAllByClosedIsFalse();
}
