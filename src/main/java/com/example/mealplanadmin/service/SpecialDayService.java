package com.example.mealplanadmin.service;

import com.example.mealplanadmin.model.SpecialDay;

public interface SpecialDayService {

    void upsert(SpecialDay specialDayDTO);
}
