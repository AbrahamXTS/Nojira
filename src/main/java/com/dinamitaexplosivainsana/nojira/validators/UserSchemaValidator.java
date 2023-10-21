package com.dinamitaexplosivainsana.nojira.validators;

import com.dinamitaexplosivainsana.nojira.exceptions.EmptyDataException;
import com.dinamitaexplosivainsana.nojira.exceptions.IncorrectEmailFormatException;
import com.dinamitaexplosivainsana.nojira.exceptions.IncorrectFullNameFormatException;


public class UserSchemaValidator{

    public static void validationsToSignUp(final String fullName, final String email, final String password)
            throws EmptyDataException, IncorrectEmailFormatException, IncorrectFullNameFormatException {

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new EmptyDataException("Todos los campos son obligatorios");
        }
        if (!isEmailFormatCorrect(email)) {
            throw new IncorrectEmailFormatException("Correo inválido. Verifique que el formato este correcto e intente de nuevo");
        }
        if (!isFullNameFormatCorrect(fullName)) {
            throw new IncorrectFullNameFormatException("Nombre inválido. Verifique que el nombre no contenga números o carácteres especiales");
        }
    }

    private static boolean isEmailFormatCorrect(String email){
        String pattern = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+";
        return email.matches(pattern);
    }

    private static boolean isFullNameFormatCorrect(String fullName){
        String pattern = "^[\\p{L} ]+$";
        return fullName.matches(pattern);
    }
}
