package com.evaluacion.productosapi.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.evaluacion.productosapi.service.exception.ProductoNoEncontradoException;
import com.evaluacion.productosapi.service.exception.StockInsuficienteException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // producto no encontrado
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleProductoNoEncontrado(ProductoNoEncontradoException ex) {
        ErrorResponse body = new ErrorResponse(
                "Producto no encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // stock insuficiente
    @ExceptionHandler({StockInsuficienteException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        ErrorResponse body = new ErrorResponse(
                "Solicitud inválida",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    // validacion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidacion(MethodArgumentNotValidException ex) {
        String detalle = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> {
                    String campo = err.getField();
                    String msg = err.getDefaultMessage();
                    return campo + ": " + msg;
                })
                .collect(Collectors.joining(", "));

        ErrorResponse body = new ErrorResponse(
                "Error de validación",
                detalle
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    // general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse body = new ErrorResponse(
                "Error interno",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
