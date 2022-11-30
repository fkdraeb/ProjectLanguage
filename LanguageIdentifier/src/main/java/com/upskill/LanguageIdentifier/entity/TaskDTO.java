package com.upskill.LanguageIdentifier.entity;

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

    public TaskDTO() {
        super();
    }

    public TaskDTO(Integer id, String url) {
        taskId = id;
        this.url = url;
    }

    public void setLanguageCode(Locale langCode) {
        this.languageCode=langCode;
    }

    public void setLanguagePT(String languagePT) {
        languagePT = languagePT.substring(0, 1).toUpperCase() + languagePT.substring(1);
        this.languagePT = languagePT;

    }

}
