package com.dinamitaexplosivainsana.nojira.domain.config;

public class Constants {
    public static final String AUTHENTICATION_FAILED_EXCEPTION_MESSAGE = "Ops! Verifica tu usuario o contraseña";
    public static final String BEARER_AUTHENTICATION_SCHEME_NAME = "bearerAuth";
    public static final String INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE = "Dirección de correo electrónico inválida. Un correo electrónico válido sólo puede contener letras, números, guiones, '@' y '.'";
    public static final String INVALID_FULL_NAME_FORMAT_EXCEPTION_MESSAGE = "Nombre inválido. Verifique que el nombre no contenga números o carácteres especiales";
    public static final String REQUIRED_ARGUMENT_EXCEPTION_MESSAGE = "Hey! Todos los campos son obligatorios";
    public static final String USER_ALREADY_EXIST_EXCEPTION_MESSAGE = "Hey! Este correo electrónico ya ha sido usado. Por favor, intenta de nuevo.";
    public static final String TASK_NOT_FOUND_EXCEPTION_MESSAGE = "La tarea que se desea eliminar no existe";
    public static final String UNAUTHORIZED_ACTION_TASK_EXCEPTION_MESSAGE = "Acceso no autorizado: no tienes permisos para realizar esta acción, ya que no eres el propietario del proyecto.";
    public static final String UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE = "Acceso no autorizado: parece que no tienes ningun tipo de relación con este proyecto.";
    public static final String TASK_NOT_FOUND_IN_PROJECT_EXCEPTION_MESSAGE = "Oops! La tarea que se desea actualizar no corresponde al proyecto.";
    public static final String TASK_NOT_FOUND_FOR_TIME_ASSIGNMENT = "No se puede asignar tiempo a la tarea, ya que no se encontró la tarea correspondiente";

    private Constants() {
    }
}
