package com.cadastro.veiculos.exceptionPersonalizada;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Object Not Found")
public class VeiculoException extends Exception{
    private static final long serialVersionUID = 1L;

    public VeiculoException (String message) {
        super(message);
    }
}
