package com.example.mealplanadmin.repository;

import com.example.mealplanadmin.model.Plan;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlanRepositoryTest {
    private final PlanRepository planRepository;

    @Autowired
    public PlanRepositoryTest(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Test
    void saveAndClosePlanAndFindAllByClosedIsFalse() {
        var planToPersist = new Plan(LocalDate.now(), 10, 5);
        var persistedPlan = planRepository.save(planToPersist);
        assertThat(planRepository.findAllByClosedIsFalse()).isEqualTo(List.of(persistedPlan));
        planRepository.closePlan(persistedPlan.id());
        assertThat(planRepository.findAllByClosedIsFalse()).isEqualTo(List.of());
    }
}
