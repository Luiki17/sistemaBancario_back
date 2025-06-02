package com.banco.sistemaBancario.exception;

public class TransferenciaInvalidaException extends RuntimeException{
    public TransferenciaInvalidaException(String message) {
        super(message);
    }
}
