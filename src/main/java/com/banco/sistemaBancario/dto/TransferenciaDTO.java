package com.banco.sistemaBancario.dto;

import com.banco.sistemaBancario.validation.CuentasDistintas;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@CuentasDistintas
public class TransferenciaDTO {

    @NotNull(message = "El ID de la cuenta de origen no puede ser nulo")
    private Long cuentaOrigenId;

    @NotNull(message = "El ID de la cuenta de destino no puede ser nulo")
    private Long cuentaDestinoId;

    @NotNull(message = "El monto de la transferencia no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto de la transferencia debe ser mayor que cero")
    private BigDecimal monto;

    private String descripcion;
}
