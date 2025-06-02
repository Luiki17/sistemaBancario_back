package com.banco.sistemaBancario.validation;

import com.banco.sistemaBancario.dto.TransferenciaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CuentasDistintasValidator implements ConstraintValidator<CuentasDistintas, TransferenciaDTO> {

    @Override
    public boolean isValid(TransferenciaDTO dto, ConstraintValidatorContext context) {
        if (dto.getCuentaOrigenId() == null || dto.getCuentaDestinoId() == null) {
            return true; // Omitir esta validación si ya hay errores de null
        }

        boolean valid = !dto.getCuentaOrigenId().equals(dto.getCuentaDestinoId());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La cuenta de origen y destino no pueden ser la misma")
                    .addPropertyNode("cuentaOrigenId") // <-- asocia el error al campo
                    .addConstraintViolation();
        }

        return valid;
    }
}
