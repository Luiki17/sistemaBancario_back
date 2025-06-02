package com.banco.sistemaBancario.exception;

public class CuentaNoEncontradaException extends RuntimeException{
    public CuentaNoEncontradaException(String message) {
        super(message);
    }
}
