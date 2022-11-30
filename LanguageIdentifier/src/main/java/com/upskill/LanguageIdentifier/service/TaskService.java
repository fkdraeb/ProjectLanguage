package com.upskill.LanguageIdentifier.service;

import com.upskill.LanguageIdentifier.InterfaceFeign;
import com.upskill.LanguageIdentifier.entity.TaskDTO;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.langdetect.optimaize.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Locale;


@Service
@RestController
public class TaskService {

    @Autowired
    InterfaceFeign interfaceFeign;

    public void setFinalDateTime(TaskDTO taskDTO) {
        taskDTO.setFinalDate(LocalDateTime.now());
    }

    public void runTask(TaskDTO taskDTO) {
        System.out.println("Começou a executar a tarefa "+ taskDTO.toString());

        try {
            setLanguage(taskDTO);
            updateStatus(taskDTO, "Concluída");
            setFinalDateTime(taskDTO);
        }

        catch (TikaException e) {
            updateStatus(taskDTO, "Cancelada", "O programa não conseguiu analisar o documento.");
            System.out.println("runTask entrou na TikaException: " + e.getLocalizedMessage());
        }
        catch (IOException e) {
            updateStatus(taskDTO, "Cancelada", "Não foi possivel aceder ao ficheiro porque o caminho introduzido está errado ou o ficheiro é ilegível.");
            System.out.println("runTask entrou no IOException: " + e.getLocalizedMessage());
        }
        catch (EmptyFileException e) {
            updateStatus(taskDTO, "Cancelada", "O ficheiro não contém texto.");
            System.out.println("runTask entrou no EmptyFileException: " + e.getLocalizedMessage());
        }
        catch (Exception e) {
            updateStatus(taskDTO, "Cancelada", "O programa não conseguiu executar a tarefa.");
            System.out.println("runTask entrou na Exception geral: " + e.getLocalizedMessage());
        }
        System.out.println("tarefa executada no LI: " + taskDTO.toString());
        String encodedUrl = Base64.getUrlEncoder().encodeToString(taskDTO.getUrl().getBytes());
        taskDTO.setUrl(encodedUrl);
        interfaceFeign.sendFinishedTask(taskDTO);
        System.out.println("LI enviou tarefa executada para DBM");
    }

    public void setLanguage(TaskDTO taskDTO) throws IOException, TikaException {
        Locale langCode = new Locale.Builder().setLanguage(identifyLanguage(taskDTO)).build();
        taskDTO.setLanguageCode(langCode);
        taskDTO.setLanguagePT(langCode.getDisplayLanguage(new Locale.Builder().setLanguage("pt").setRegion("PT").build()));
    }

    public void updateStatus(TaskDTO taskDTO, String status) {
        taskDTO.setStatus(status);
        taskDTO.setFinalDate(LocalDateTime.now());
    }

    public void updateStatus(TaskDTO taskDTO, String status, String cause) {
        taskDTO.setStatus(status);
        taskDTO.setCancelationCause(cause);
        taskDTO.setFinalDate(LocalDateTime.now());
    }

    public String identifyLanguage(TaskDTO taskDTO) throws IOException, TikaException {
        URL url = new URL(taskDTO.getUrl());
        Tika tika = new Tika();
        String content = tika.parseToString(url);
        if (content.isBlank() )
            throw new EmptyFileException();
        LanguageDetector detector = new OptimaizeLangDetector().loadModels();
        LanguageResult result = detector.detect(content);
        System.out.println("LI identificou a língua:" + result.getLanguage());
        return result.getLanguage();
    }

}