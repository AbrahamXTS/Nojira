package com.dinamitaexplosivainsana.nojira.domain.models;

import java.util.Date;

public record Task(String id, String description, Integer timeEstimatedInMinutes, String title, Integer timeUsedInMinutes) {
}
