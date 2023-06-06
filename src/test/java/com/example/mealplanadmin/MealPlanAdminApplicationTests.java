package com.example.mealplanadmin;

import com.example.mealplanadmin.controller.PlanController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MealPlanAdminApplicationTests {

    @Autowired
    PlanController planController;

    @Test
    void contextLoads() {
        assertThat(planController).isNotNull();
    }

}
