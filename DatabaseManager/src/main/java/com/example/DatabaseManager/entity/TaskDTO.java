package com.example.DatabaseManager.entity;

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

    public TaskDTO(Integer idTask, String url, LocalDateTime initialDate, String status) {
        this.taskId = idTask;
        this.url = url;
        this.initialDate = initialDate;
        this.status = status;
    }

    public TaskDTO() {
        super();
    }

    public TaskDTO(Integer idTask, String url, LocalDateTime initialDate, LocalDateTime finalDate, String status, Locale languageCode, String languagePT, String cancelationCause) {
        this.taskId = idTask;
        this.url = url;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.status = status;
        this.languageCode = languageCode;
        this.languagePT = languagePT;
        this.cancelationCause = cancelationCause;
    }
}

