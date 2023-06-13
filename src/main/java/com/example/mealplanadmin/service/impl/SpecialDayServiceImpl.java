package com.example.mealplanadmin.service.impl;

import com.example.mealplanadmin.model.SpecialDay;
import com.example.mealplanadmin.repository.SpecialDayRepository;
import com.example.mealplanadmin.service.SpecialDayService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialDayServiceImpl implements SpecialDayService {

    private final SpecialDayRepository specialDayRepository;

    @Autowired
    public SpecialDayServiceImpl(SpecialDayRepository specialDayRepository) {
        this.specialDayRepository = specialDayRepository;
    }

    @Override
    @Transactional
    public void upsert(SpecialDay specialDay) {
        specialDayRepository.upsert(specialDay);
    }
}
