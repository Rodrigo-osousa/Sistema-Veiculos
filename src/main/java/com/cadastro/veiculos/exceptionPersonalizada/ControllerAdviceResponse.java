package com.cadastro.veiculos.exceptionPersonalizada;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceResponse {
    @ExceptionHandler(VeiculoException.class)
    public ResponseEntity<String> handleEmptyImput(VeiculoException veiculoException) {
        return new ResponseEntity<String>(veiculoException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
