package com.example.mealplanadmin.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.mealplanadmin.config.SecurityConfig;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mealplanadmin.model.CreatePlanDTO;
import com.example.mealplanadmin.model.PlanDTO;
import com.example.mealplanadmin.service.PlanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = PlanController.class, properties = {"api.secret=myApiSecret"}, excludeAutoConfiguration = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@Import(SecurityConfig.class)
class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlanService planService;

    @Test
    void testCreatePlan() throws Exception {
        var today = LocalDate.now();
        var totalDays = 30;
        var mealsPerDay = 4;
        var requestObject = new CreatePlanDTO(totalDays, mealsPerDay, Optional.of(today));
        var expectedPlan = new PlanDTO(today, totalDays, mealsPerDay, today.minusDays(1));
        var expectedJson = objectMapper.writeValueAsString(expectedPlan);
        when(planService.create(any())).thenReturn(expectedPlan);
        mockMvc.perform(post("/plans")
                .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", "myApiSecret")
                        .content(objectMapper.writeValueAsString(requestObject))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
