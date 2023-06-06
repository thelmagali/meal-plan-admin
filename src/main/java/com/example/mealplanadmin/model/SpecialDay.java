package com.example.mealplanadmin.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "special_days")
public class SpecialDay {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "meals")
    Integer meals;

    public SpecialDay(LocalDate date, Integer meals) {
        this.date = date;
        this.meals = meals;
    }

    public SpecialDay() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMeals() {
        return meals;
    }

    public void setMeals(Integer meals) {
        this.meals = meals;
    }
}
