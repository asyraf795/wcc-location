package com.test.wcc.validator;

import com.test.wcc.annotation.ValidDecimalPoint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalPointValidator implements ConstraintValidator<ValidDecimalPoint, BigDecimal>  {
    private ValidDecimalPoint annotation;

    @Override
    public void initialize(ValidDecimalPoint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        BigDecimal ceiling = value.setScale(annotation.decimal(), RoundingMode.CEILING);
        return value.compareTo(ceiling) == 0;
    }
}