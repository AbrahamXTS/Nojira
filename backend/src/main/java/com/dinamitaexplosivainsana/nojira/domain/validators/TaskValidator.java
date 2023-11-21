package com.dinamitaexplosivainsana.nojira.domain.validators;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTrackingDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.TaskNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import java.util.List;
import java.util.Objects;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.TASK_TRACKING_NOT_FOUND_EXCEPTION_MESSAGE;
public class TaskValidator {
    public static void validateTaskId(String findTask, TaskTrackingDTO taskTrackingDTO){
        if (findTask == null || !findTask.equals(taskTrackingDTO.taskId())) {
            throw new TaskNotFoundException(TASK_TRACKING_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
    public static void validateTaskLists(Task findTask, List<Task> tasksByUserId, List<Task> tasksByProjectId){
        boolean tasksAreNull = Objects.isNull(tasksByUserId)
                ||Objects.isNull(tasksByProjectId)
                ||Objects.isNull(findTask);
        if (tasksAreNull||!(tasksByUserId.contains(findTask)) ||!(tasksByProjectId.contains(findTask)) ){
            throw new TaskNotFoundException(TASK_TRACKING_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
