package com.example.mealplanadmin.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public Plan(LocalDate startDate, Integer totalDays, Integer mealsPerDay) {
        this.startDate = startDate;
        this.totalDays = totalDays;
        this.mealsPerDay = mealsPerDay;
        this.closed = false;
    }

    public Plan() {

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

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", totalDays=" + totalDays +
                ", mealsPerDay=" + mealsPerDay +
                ", closed=" + closed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plan plan)) return false;
        return Objects.equals(getId(), plan.getId()) && Objects.equals(getStartDate(), plan.getStartDate()) && Objects.equals(getTotalDays(), plan.getTotalDays()) && Objects.equals(getMealsPerDay(), plan.getMealsPerDay()) && Objects.equals(getClosed(), plan.getClosed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartDate(), getTotalDays(), getMealsPerDay(), getClosed());
    }
}
