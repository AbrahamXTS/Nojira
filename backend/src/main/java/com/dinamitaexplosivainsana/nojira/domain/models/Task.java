package com.dinamitaexplosivainsana.nojira.domain.models;

public record Task(
        String id,
        String description,
        Integer timeEstimatedInMinutes,
        Integer timeUsedInMinutes,
        String projectId,
        String userId,
        String title,
        Integer statusId,
        User userAssigned,
        Project projectBelonging,
        Status status
) {
    public Task(String id, String description, Integer timeEstimatedInMinutes, Integer timeUsedInMinutes, String projectId, String userId, String title, Integer statusId) {
        this(id, description, timeEstimatedInMinutes, timeUsedInMinutes, projectId, userId, title, statusId, null, null, null);
    }

    public Task(String id, String description, Integer timeEstimatedInMinutes, String title, Integer timeUsedInMinutes) {
        this(id, description, timeEstimatedInMinutes, timeUsedInMinutes, null, null, title, null, null, null, null);
    }

    public Task(String id, String description, Integer timeEstimatedInMinutes, String title, Integer timeUsedInMinutes, User userAssigned, Project projectBelonging, Status status) {
        this(id, description, timeEstimatedInMinutes, timeUsedInMinutes, null, null, title, null, userAssigned, projectBelonging, status);
    }
}



