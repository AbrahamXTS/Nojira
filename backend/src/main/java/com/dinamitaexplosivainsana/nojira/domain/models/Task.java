package com.dinamitaexplosivainsana.nojira.domain.models;
import java.util.Date;
public record Task(String id, String title, String description,
                   String status, Integer timeEstimatedInMinutes,
                   Integer timeUsedInMinutes,User userAsigned) {}
