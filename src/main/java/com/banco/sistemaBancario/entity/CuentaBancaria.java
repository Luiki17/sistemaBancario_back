package com.banco.sistemaBancario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas_bancarias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;

    private BigDecimal saldo;

    private String tipoCuenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
