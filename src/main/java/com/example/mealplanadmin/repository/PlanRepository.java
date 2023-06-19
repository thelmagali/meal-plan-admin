package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
    @Modifying(clearAutomatically = true)
    @Query("update PlanEntity p set p.closed = true where p.id = :id")
    void closePlan(@Param("id") Long id);


    @Query("select new com.example.mealplanadmin.model.Plan(p) from PlanEntity p where p.closed = false")
    List<Plan> findAllByClosedIsFalse();

    @Transactional
    @Modifying
    default Plan save(Plan planRecord) {
        var plan = save(planRecord.toEntity());
        return new Plan(plan);
    }
}
