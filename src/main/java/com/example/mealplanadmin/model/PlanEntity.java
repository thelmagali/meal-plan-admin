package com.example.mealplanadmin.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "plans")
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "total_days")
    Integer totalDays;

    @Column(name = "meals_per_day")
    Integer mealsPerDay;

    @Column(name = "closed")
    Boolean closed;

    public PlanEntity(LocalDate startDate, Integer totalDays, Integer mealsPerDay) {
        this.startDate = startDate;
        this.totalDays = totalDays;
        this.mealsPerDay = mealsPerDay;
        this.closed = false;
    }

    public PlanEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(Integer mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
