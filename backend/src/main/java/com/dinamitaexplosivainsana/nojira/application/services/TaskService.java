package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.StatusCatalogRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.ResourceNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.PermissionsValidator;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskDataValidator;

import java.util.List;
import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.PROJECT_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.TASK_NOT_FOUND_EXCEPTION_MESSAGE;
/**
 * Service that manages operations related to task management in projects.
 */
public class TaskService {
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final StatusCatalogRepository statusCatalogRepository;
    private final TaskRepository taskRepository;
     /**
     * Constructor for the task service.
     *
     * @param projectRepository       Project repository used to access and manipulate project information.
     * @param roleRepository          Role repository used to access and manipulate information about roles associated with projects.
     * @param statusCatalogRepository Status catalog repository used to access and manipulate information about task statuses.
     * @param taskRepository          Task repository used to access and manipulate information about tasks in projects.
     */
    public TaskService(
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            StatusCatalogRepository statusCatalogRepository,
            TaskRepository taskRepository
    ) {
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.statusCatalogRepository = statusCatalogRepository;
        this.taskRepository = taskRepository;
    }
    /**
     * Retrieves information about a task specified by its unique identifier.
     *
     * @param taskId    Unique identifier of the task to be retrieved.
     * @param userId    Unique identifier of the user making the request.
     * @param projectId Unique identifier of the project to which the task belongs.
     * @return {@code TaskDTO} object with information about the task.
     * @throws ResourceNotFoundException If the task or the project is not found in the system.
     */
    public TaskDTO getTask(String taskId, String userId, String projectId) {
        Task findedTask = this.taskRepository.getTaskByTaskId(taskId);

        Role roleBetweenUserAndProject = this.roleRepository
                .findRoleBetweenUserAndProject(userId, projectId);

        PermissionsValidator.validateAccess(roleBetweenUserAndProject);

        return new TaskDTO(
                findedTask.id(),
                findedTask.title(),
                findedTask.description(),
                findedTask.statusCatalog().type(),
                new TimesDTO(
                        findedTask.timeEstimatedInMinutes(),
                        findedTask.timeUsedInMinutes()
                ),
                new OwnerDTO(
                        findedTask.user().id(),
                        findedTask.user().fullName()
                )
        );
    }
    /**
     * Retrieves the list of tasks associated with a project specified by its unique identifier.
     *
     * @param projectId Unique identifier of the project for which the list of tasks is to be obtained.
     * @return {@code ProjectWithTasksDTO} object with information about the project and its list of associated tasks.
     * @throws ResourceNotFoundException If the project is not found in the system.
     */
    public ProjectWithTasksDTO getAllTasksByProjectId(String projectId) {
        Project project = projectRepository.getProjectByProjectId(projectId);

        if (Objects.isNull(project)) {
            throw new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        List<TaskDTO> tasks = taskRepository.getAllTasksByProjectId(projectId).stream()
                .map(task -> new TaskDTO(
                                task.id(),
                                task.title(),
                                task.description(),
                                task.statusCatalog().type(),
                                new TimesDTO(task.timeEstimatedInMinutes(), task.timeUsedInMinutes()),
                                new OwnerDTO(task.user().id(), task.user().fullName())
                        )
                )
                .toList();

        return new ProjectWithTasksDTO(project.id(), project.name(), tasks);
    }
    /**
     * Creates a new task in the system.
     *
     * @param task      {@code TaskCreateDTO} object containing the information of the new task.
     * @param userId    Unique identifier of the user making the request.
     * @param projectId Unique identifier of the project to which the task belongs.
     * @return {@code TaskDTO} object with information about the created task.
     * @throws ResourceNotFoundException If the project is not found in the system.
     */
    public TaskDTO createTask(TaskCreateDTO task, String userId, String projectId) {
        TaskDataValidator.validateTaskDataIsNotEmpty(task.title(), task.description());

        Role roleBetweenUserAndProject = this.roleRepository
                .findRoleBetweenUserAndProject(userId, projectId);

        PermissionsValidator.validateAccess(roleBetweenUserAndProject);

        StatusCatalog status = this.statusCatalogRepository.getStatusByStatusName(StatusCatalogEnum.TO_DO.getType());

        Task taskToSave = new Task(
                null,
                task.description(),
                0,
                task.title(),
                0,
                roleBetweenUserAndProject.project(),
                roleBetweenUserAndProject.user(),
                status
        );

        Task taskSaved = this.taskRepository.saveTask(taskToSave);

        return new TaskDTO(
                taskSaved.id(),
                taskSaved.title(),
                taskSaved.description(),
                taskSaved.statusCatalog().type(),
                new TimesDTO(
                        taskSaved.timeEstimatedInMinutes(),
                        taskSaved.timeUsedInMinutes()
                ),
                new OwnerDTO(
                        taskSaved.user().id(),
                        taskSaved.user().fullName()
                )
        );
    }
    /**
     * Deletes a task specified by its unique identifier.
     *
     * @param taskId    Unique identifier of the task to be deleted.
     * @param userId    Unique identifier of the user making the request.
     * @param projectId Unique identifier of the project to which the task belongs.
     * @return {@code TaskDTO} object with information about the deleted task.
     * @throws ResourceNotFoundException If the task is not found in the system.
     */
    public TaskDTO deleteTask(String taskId, String userId, String projectId) {
        Task findTask = this.taskRepository.getTaskByTaskId(taskId);

        List<Task> tasksByUserId = this.taskRepository.getAllTasksByUserId(userId);

        List<Task> tasksByProjectId = this.taskRepository.getAllTasksByProjectId(projectId);

        boolean tasksAreNull = Objects.isNull(tasksByUserId)
                || Objects.isNull(tasksByProjectId)
                || Objects.isNull(findTask);

        if (tasksAreNull || !(tasksByUserId.contains(findTask)) || !(tasksByProjectId.contains(findTask))) {
            throw new ResourceNotFoundException(TASK_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        Task deletedTask = this.taskRepository.deleteTaskByTaskId(taskId);

        return new TaskDTO(
                deletedTask.id(),
                deletedTask.title(),
                deletedTask.description(),
                deletedTask.statusCatalog().type(),
                new TimesDTO(deletedTask.timeEstimatedInMinutes(), deletedTask.timeUsedInMinutes()),
                new OwnerDTO(deletedTask.user().id(), deletedTask.user().fullName())
        );
    }
    /**
     * Updates the information of a task specified by its unique identifier.
     *
     * @param task      {@code TaskDTO} object containing the updated information of the task.
     * @param userId    Unique identifier of the user making the request.
     * @param projectId Unique identifier of the project to which the task belongs.
     * @param taskId    Unique identifier of the task to be updated.
     * @return {@code TaskDTO} object with the updated information of the task.
     * @throws ResourceNotFoundException If the task is not found in the system.
     */
    public TaskDTO updateTask(TaskDTO task, String userId, String projectId, String taskId) {
        TaskDataValidator.validateTaskDataIsNotEmpty(task.title(), task.description());

        Task findedTask = this.taskRepository.getTaskByTaskId(taskId);

        Role roleBetweenUserAndProject = this.roleRepository
                .findRoleBetweenUserAndProject(userId, projectId);

        PermissionsValidator.validateAccess(roleBetweenUserAndProject);

        Task taskUpdated = taskRepository.updateTaskByTaskId(
                taskId,
                new Task(
                        findedTask.id(),
                        task.description(),
                        task.times().estimated(),
                        task.title(),
                        task.times().used(),
                        roleBetweenUserAndProject.project(),
                        roleBetweenUserAndProject.user(),
                        statusCatalogRepository.getStatusByStatusName(task.status())
                )
        );

        return new TaskDTO(
                taskId,
                taskUpdated.title(),
                taskUpdated.description(),
                taskUpdated.statusCatalog().type(),
                new TimesDTO(
                        taskUpdated.timeEstimatedInMinutes(),
                        taskUpdated.timeUsedInMinutes()
                ),
                new OwnerDTO(
                        taskUpdated.user().id(),
                        taskUpdated.user().fullName()
                )

        );
    }
}
