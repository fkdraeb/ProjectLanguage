package com.example.DatabaseManager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name="task")
public @Data class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    @Column(length = 500)
    private String url;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String status;
    private Locale languageCode;
    private String languagePT;
    private String cancelationCause;

    public Task() {
        super();
    }

    public Task (int id, String url) {
        taskId = id;
        this.url = url;
    }

    public Task (String url) {
        this.url = url;
    }


}

