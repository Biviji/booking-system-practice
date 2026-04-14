package com.example.booking_system_practice.annotation;

import com.example.booking_system_practice.dto.request.CreateReservationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDates, CreateReservationRequest> {

    @Override
    public void initialize(ValidDates constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreateReservationRequest request, ConstraintValidatorContext context) {
        if (request.getCheckInDate() == null || request.getCheckOutDate() == null) {
            return true;
        }
        return request.getCheckOutDate().isAfter(request.getCheckInDate());
    }



}
