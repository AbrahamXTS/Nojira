package com.dinamitaexplosivainsana.nojira.domain.models;

import java.util.Date;

public record Task(String id, String description, Date estimated, String title, Date total) {
}
