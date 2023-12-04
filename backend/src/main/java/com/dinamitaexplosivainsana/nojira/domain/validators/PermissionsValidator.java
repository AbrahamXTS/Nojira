package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedAccessException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;
/**
 * Utility class for validating permissions or access control related to user roles and projects.
 */
public class PermissionsValidator {
    private PermissionsValidator() {
    }
 /**
     * Validates access based on the role between the user and the project.
     *
     * @param roleBetweenUserAndProject The role between the user and the project.
     * @throws UnauthorizedAccessException If access is unauthorized.
     */
    public static void validateAccess(Role roleBetweenUserAndProject) {
        validateRelatedBetweenUserAndProject(roleBetweenUserAndProject);
        validateUserRoleInProject(roleBetweenUserAndProject.roleCatalog());
    }
     /**
     * Validates if there is a related role between the user and the project.
     *
     * @param roleBetweenUserAndProject The role between the user and the project.
     * @throws UnauthorizedAccessException If no related role is found.
     */
    private static void validateRelatedBetweenUserAndProject(Role roleBetweenUserAndProject) {
        if (Objects.isNull(roleBetweenUserAndProject)) {
            throw new UnauthorizedAccessException(UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE);
        }
    }
     /**
     * Validates the user's role in the project.
     *
     * @param userRole The user's role in the project.
     * @throws UnauthorizedAccessException If the user's role is not authorized for the action.
     */
    private static void validateUserRoleInProject(RoleCatalog userRole) {
        if (!Objects.equals(userRole.id(), RoleCatalogEnum.OWNER.getId())) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACTION_EXCEPTION_MESSAGE);
        }
    }
}
