package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedAccessException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;

public class PermissionsValidator {
    private PermissionsValidator() {
    }

    public static void validateAccess(Role roleBetweenUserAndProject) {
        validateRelatedBetweenUserAndProject(roleBetweenUserAndProject);
        validateUserRoleInProject(roleBetweenUserAndProject.roleCatalog());
    }

    private static void validateRelatedBetweenUserAndProject(Role roleBetweenUserAndProject) {
        if (Objects.isNull(roleBetweenUserAndProject)) {
            throw new UnauthorizedAccessException(UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateUserRoleInProject(RoleCatalog userRole) {
        if (!Objects.equals(userRole.id(), RoleCatalogEnum.OWNER.getId())) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACTION_EXCEPTION_MESSAGE);
        }
    }
}
