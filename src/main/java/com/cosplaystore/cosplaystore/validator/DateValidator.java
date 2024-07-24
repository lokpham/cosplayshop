package com.cosplaystore.cosplaystore.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.cosplaystore.cosplaystore.annotation.ValidDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        try {

            LocalDate.parse(arg0, DATE_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
