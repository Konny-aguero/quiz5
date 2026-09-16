package com.logiexpress.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PesoExcedidoException.class)
    public ProblemDetail handlePesoExcedido(PesoExcedidoException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        
        problemDetail.setType(URI.create("https://logiexpress.com/errors/peso-excedido"));
        problemDetail.setTitle("Peso Excedido");
        String path = request.getDescription(false).replace("uri=", "");
        problemDetail.setInstance(URI.create(path));
        problemDetail.setProperty("timestamp", Instant.now());
        
        return problemDetail;
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ProblemDetail handleClienteNoEncontrado(ClienteNoEncontradoException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        
        problemDetail.setType(URI.create("https://logiexpress.com/errors/cliente-no-encontrado"));
        problemDetail.setTitle("Cliente No Encontrado");
        
        String path = request.getDescription(false).replace("uri=", "");
        problemDetail.setInstance(URI.create(path));
        problemDetail.setProperty("timestamp", Instant.now());
        
        return problemDetail;
    }
}