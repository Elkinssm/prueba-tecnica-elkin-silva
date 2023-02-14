package com.elkin.pruebaTecnica.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppExceptions extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public AppExceptions(String message , HttpStatus htppStatus) {
        super(message);
        this.message = message;
        this.httpStatus = htppStatus;
    }
}
