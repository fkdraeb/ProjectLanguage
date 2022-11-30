package com.upskill.TaskManager.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Locale;


public @Data class TaskDTO {

    private Integer taskId;
    private String url;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String status;
    private Locale languageCode;
    private String languagePT;
    private String cancelationCause;


    public TaskDTO(Integer taskId, String url) {
        this.taskId = taskId;
        this.url = url;
    }

    public TaskDTO() {super();}

}

