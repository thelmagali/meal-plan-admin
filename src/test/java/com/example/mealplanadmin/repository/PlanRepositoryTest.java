package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.Plan;
import com.example.mealplanadmin.model.PlanEntity;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlanRepositoryTest {
    private final TestEntityManager entityManager;

    private final PlanRepository planRepository;

    @Autowired
    public PlanRepositoryTest(TestEntityManager entityManager, PlanRepository planRepository) {
        this.entityManager = entityManager;
        this.planRepository = planRepository;
    }

    @Test
    void closePlanAndFindAllByClosedIsFalse() {
        var planToPersist = new PlanEntity(LocalDate.now(), 10, 5);
        assertThat(planToPersist.getClosed()).isFalse();
        var persistedPlanEntity = entityManager.persist(planToPersist);
        var persistedPlan = new Plan(persistedPlanEntity);
        assertThat(planRepository.findAllByClosedIsFalse()).isEqualTo(List.of(persistedPlan));
        planRepository.closePlan(persistedPlanEntity.getId());
        entityManager.flush();
        var foundPlan = entityManager.find(PlanEntity.class, persistedPlanEntity.getId());
        assertThat(foundPlan.getClosed()).isTrue();
        assertThat(planRepository.findAllByClosedIsFalse()).isEqualTo(List.of());
    }
}
