package com.banco.sistemaBancario.service;

import com.banco.sistemaBancario.dto.TransferenciaDTO;
import com.banco.sistemaBancario.entity.CuentaBancaria;
import com.banco.sistemaBancario.entity.Transaccion;
import com.banco.sistemaBancario.exception.CuentaNoEncontradaException;
import com.banco.sistemaBancario.exception.SaldoInsuficienteException;
import com.banco.sistemaBancario.exception.TransferenciaInvalidaException;
import com.banco.sistemaBancario.repository.CuentaBancariaRepository;
import com.banco.sistemaBancario.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransaccionService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Transaccion transferir(TransferenciaDTO dto){
        CuentaBancaria origen = cuentaBancariaRepository.findById(dto.getCuentaOrigenId())
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta de origen no encontrada"));
        CuentaBancaria destino = cuentaBancariaRepository.findById(dto.getCuentaDestinoId())
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta de destino no encontrada"));

        BigDecimal monto = dto.getMonto();

        if(origen.getSaldo().compareTo(monto) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente en la cuenta de origen");
        }

        if (origen.getId().equals(destino.getId())) {
            throw new TransferenciaInvalidaException("La cuenta de origen y destino no pueden ser la misma");
        }


        //Restar y sumar saldos
        origen.setSaldo(origen.getSaldo().subtract(monto));
        destino.setSaldo(destino.getSaldo().add(monto));

        //Guardar cambios
        cuentaBancariaRepository.save(origen);
        cuentaBancariaRepository.save(destino);

        //Registrar transacción
        Transaccion transaccion = Transaccion.builder()
                .cuentaOrigen(origen)
                .cuentaDestino(destino)
                .monto(monto)
                .descripcion(dto.getDescripcion())
                .fecha(LocalDateTime.now())
                .build();

        return transaccionRepository.save(transaccion);
    }
}
