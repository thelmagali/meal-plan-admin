package com.example.mealplanadmin.controller;

import com.example.mealplanadmin.model.EmptyJson;
import com.example.mealplanadmin.model.SpecialDayDTO;
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
    public EmptyJson modifyDay(@RequestBody SpecialDayDTO specialDayDTO) {
        specialDayService.upsert(specialDayDTO);
        return new EmptyJson();
    }
}
