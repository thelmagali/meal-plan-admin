package com.example.mealplanadmin.exception;

public class TooManyPendingPlansException extends RuntimeException {
    @Override
    public String getLocalizedMessage() {
        return "There is already one pending plan.";
    }
}
