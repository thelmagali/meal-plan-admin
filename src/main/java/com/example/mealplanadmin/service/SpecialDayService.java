package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.SpecialDayDTO;

public interface SpecialDayService {

    void upsert(SpecialDayDTO specialDayDTO);
}
