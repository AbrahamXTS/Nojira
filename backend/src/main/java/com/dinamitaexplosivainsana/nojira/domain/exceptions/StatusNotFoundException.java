package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class StatusNotFoundException extends RuntimeException{
    public StatusNotFoundException(String message){
        super(message);
    }
}
