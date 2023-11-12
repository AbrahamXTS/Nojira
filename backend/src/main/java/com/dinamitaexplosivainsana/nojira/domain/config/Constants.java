package com.dinamitaexplosivainsana.nojira.domain.config;

public class Constants {
    public static final String AUTHENTICATION_FAILED_EXCEPTION_MESSAGE = "Ops! Verifica tu usuario o contraseña";
    public static final String BEARER_AUTHENTICATION_SCHEME_NAME = "bearerAuth";
    public static final String INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE = "Dirección de correo electrónico inválida. Un correo electrónico válido sólo puede contener letras, números, guiones, '@' y '.'";
    public static final String INVALID_FULL_NAME_FORMAT_EXCEPTION_MESSAGE = "Nombre inválido. Verifique que el nombre no contenga números o carácteres especiales";
    public static final String REQUIRED_ARGUMENT_EXCEPTION_MESSAGE = "Hey! Todos los campos son obligatorios";
    public static final String USER_ALREADY_EXIST_EXCEPTION_MESSAGE = "Hey! Este correo electrónico ya ha sido usado. Por favor, intenta de nuevo.";
    public static final String TASK_NOT_FOUND_EXCEPTION_MESSAGE = "La tarea que se desea eliminar no existe";
    private Constants() {
    }
}
