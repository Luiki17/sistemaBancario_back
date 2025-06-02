package com.banco.sistemaBancario.controller;

import com.banco.sistemaBancario.entity.CuentaBancaria;
import com.banco.sistemaBancario.entity.Usuario;
import com.banco.sistemaBancario.repository.CuentaBancariaRepository;
import com.banco.sistemaBancario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaBancariaController {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/crear/{usuarioId}")
    public CuentaBancaria crearCuenta(@PathVariable Long usuarioId, @RequestBody CuentaBancaria cuenta){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        cuenta.setUsuario(usuarioOpt.get());

        //Generar un número de cuenta aleatorio
        cuenta.setNumeroCuenta("ES" + (int)(Math.random() * 1000000000));
        cuenta.setSaldo(BigDecimal.ZERO); // Inicializar saldo a cero

        return cuentaBancariaRepository.save(cuenta);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<CuentaBancaria> obtenerCuentasPorUsuario(@PathVariable Long usuarioId) {
        return cuentaBancariaRepository.findByUsuarioId(usuarioId);
    }
}
