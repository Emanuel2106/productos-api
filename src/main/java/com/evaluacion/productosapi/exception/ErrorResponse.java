package com.evaluacion.productosapi.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    // Atributos
    private String error;
    private String detalle;
    private LocalDateTime timestamp;


    // Constructor
    public ErrorResponse(String error, String detalle) {
        this.error = error;
        this.detalle = detalle;
        this.timestamp = LocalDateTime.now();
    }


    // Getters y Setters
    public String getError() {
        return error;
    }

    public String getDetalle() {
        return detalle;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
