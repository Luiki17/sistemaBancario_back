package com.banco.sistemaBancario.controller;

import com.banco.sistemaBancario.dto.TransferenciaDTO;
import com.banco.sistemaBancario.entity.Transaccion;
import com.banco.sistemaBancario.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@Valid @RequestBody TransferenciaDTO dto) {
        Transaccion transaccion = transaccionService.transferir(dto);
        return ResponseEntity.ok(transaccion);
    }

}
