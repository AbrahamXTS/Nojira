package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.UserNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.User;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE;

public class UserManagerValidator {

    public static void validate(final User user) throws UserNotFoundException {
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
