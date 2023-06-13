package com.example.mealplanadmin.controller;

import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.service.SpecialDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("special-days")
public class SpecialDayController {

    private final SpecialDayService specialDayService;

    @Autowired
    public SpecialDayController(SpecialDayService specialDayService) {
        this.specialDayService = specialDayService;
    }

    @PutMapping
    public void modifyDay(@RequestBody SpecialDay specialDayDTO) {
        specialDayService.upsert(specialDayDTO);
    }
}
