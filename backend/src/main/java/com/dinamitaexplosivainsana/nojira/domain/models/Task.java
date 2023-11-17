package com.dinamitaexplosivainsana.nojira.domain.models;

import java.util.Date;


public record Task(String id, String description, Integer estimated, Integer total, String projectId, String userId, String title, Integer statusId) {

    public Task(String id, String description, Integer timeEstimatedInMinutes, String title, Integer timeUsedInMinutes) {
        this(id, description, timeEstimatedInMinutes, timeUsedInMinutes, null, null, title, null);
    }
}
