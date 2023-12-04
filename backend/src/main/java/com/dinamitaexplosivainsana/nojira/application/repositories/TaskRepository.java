package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Task;

import java.util.List;
/**
 * Interface that provides methods to access and manipulate tasks in the system.
 */
public interface TaskRepository {
    /**
     * Retrieves a task based on its unique identifier.
     *
     * @param taskId The unique identifier of the task to retrieve.
     * @return The task corresponding to the provided unique identifier.
     */
    Task getTaskByTaskId(String taskId);
    /**
     * Retrieves all tasks associated with a specific user.
     *
     * @param userId The unique identifier of the user for whom tasks are to be retrieved.
     * @return List of tasks associated with the specified user.
     */
    List<Task> getAllTasksByUserId(String userId);
    /**
     * Retrieves all tasks associated with a specific project.
     *
     * @param projectId The unique identifier of the project for which tasks are to be retrieved.
     * @return List of tasks associated with the specified project.
     */
    List<Task> getAllTasksByProjectId(String projectId);
     /**
     * Saves a new task in the system.
     *
     * @param task The task to be saved in the system.
     * @return The task that has been saved in the system.
     */
    Task saveTask(Task task);
    /**
     * Deletes a task based on its unique identifier.
     *
     * @param taskId The unique identifier of the task to be deleted.
     * @return The task that has been deleted from the system.
     */
    Task deleteTaskByTaskId(String taskId);
     /**
     * Updates a task based on its unique identifier.
     *
     * @param taskId The unique identifier of the task to be updated.
     * @param task   The task with the new data to be used for the update.
     * @return The updated task in the system.
     */
    Task updateTaskByTaskId(String taskId, Task task);
}
