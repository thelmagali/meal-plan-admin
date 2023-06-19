package com.example.mealplanadmin.controller;

import com.example.mealplanadmin.model.SpecialDayDTO;
import com.example.mealplanadmin.service.SpecialDayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SpecialDayController.class)
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
                        .content(objectMapper.writeValueAsString(requestObject))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}
