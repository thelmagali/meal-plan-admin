package com.example.mealplanadmin.controller;

import com.example.mealplanadmin.config.SecurityConfig;
import com.example.mealplanadmin.model.SpecialDayDTO;
import com.example.mealplanadmin.service.SpecialDayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SpecialDayController.class, properties = {"api.secret=myApiSecret"}, excludeAutoConfiguration = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@Import(SecurityConfig.class)
class SpecialDayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SpecialDayService specialDayService;

    @Test
    void testCreatePlan() throws Exception {
        var today = LocalDate.now();
        var requestObject = new SpecialDayDTO(today, 2);
        doNothing().when(specialDayService).upsert(requestObject);
        mockMvc.perform(put("/special-days")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", "myApiSecret")
                        .content(objectMapper.writeValueAsString(requestObject))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void failIfNoApiKeyHeader() throws Exception {
        var today = LocalDate.now();
        var requestObject = new SpecialDayDTO(today, 2);
        doNothing().when(specialDayService).upsert(requestObject);
        mockMvc.perform(put("/special-days")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestObject))).andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
