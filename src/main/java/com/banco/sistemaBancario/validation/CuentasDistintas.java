package com.banco.sistemaBancario.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CuentasDistintasValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CuentasDistintas {
    String message() default "La cuenta de origen y destino no pueden ser la misma";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
