package com.test.wcc.annotation;

import com.test.wcc.validator.DecimalPointValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DecimalPointValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDecimalPoint {
    int decimal() default 6;

    String message() default "decimal.is.long";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}