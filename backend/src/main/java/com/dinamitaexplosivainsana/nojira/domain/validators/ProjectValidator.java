package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidUUIDException;

import java.util.regex.Pattern;

public  class ProjectValidator {
    public ProjectValidator() {
    }
    public static void validate(String id){
        if (!isUUIDValid(id)) {
            throw new InvalidUUIDException("El formato del UUID no es válido");
        }
    }

    private static boolean isUUIDValid(String id){
        Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        return UUID_REGEX.matcher(id).matches();
    }
}
