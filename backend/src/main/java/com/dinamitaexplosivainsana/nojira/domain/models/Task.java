package com.dinamitaexplosivainsana.nojira.domain.models;

public record Task(String id, String description, Integer estimatedInMinutes, String title, Integer usedInMinutes) {
}
