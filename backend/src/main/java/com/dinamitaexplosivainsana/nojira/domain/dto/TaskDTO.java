package com.dinamitaexplosivainsana.nojira.domain.dto;


import com.dinamitaexplosivainsana.nojira.domain.models.Task;

public record TaskDTO(String taskId, String title, String description, StatusDTO status, TimesDTO times, AssignedDTO assigned) {

    public TaskDTO(String title,String description){
        this(null, title,description,null,null,null);
    }
}



