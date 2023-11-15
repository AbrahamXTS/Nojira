package com.dinamitaexplosivainsana.nojira.domain.models;

import java.util.Date;

public record Task(String id, String description, Integer estimated, Integer total,String projectId,String userId, String title,Integer statusId) {
}
