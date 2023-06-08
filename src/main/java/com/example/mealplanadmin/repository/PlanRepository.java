package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
    @Transactional
    @Modifying
    @Query("update PlanEntity p set p.closed = true where p.id = :id")
    void closePlan(Long id);


    @Query("select com.example.mealplanadmin.model.Plan.fromPlan(p) from PlanEntity p where p.closed = false")
    List<Plan> findAllByClosedIsFalse();

    @Transactional
    @Modifying
    default Plan save(Plan planRecord) {
        var plan = save(planRecord.toEntity());
        return Plan.fromEntity(plan);
    }
}
