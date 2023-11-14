package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;
import com.dinamitaexplosivainsana.nojira.domain.models.User;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;

public class UserManagerValidator {

    public static void validate(final User user) throws RequiredArgumentException {
        if (Objects.isNull(user)) {
            throw new RequiredArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
