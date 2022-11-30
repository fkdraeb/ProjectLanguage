package com.upskill.TaskManager.service;

import com.upskill.TaskManager.OpenFeignInterface;
import com.upskill.TaskManager.entity.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
@EnableFeignClients
public class TaskService {

    @Autowired
    OpenFeignInterface openFeignInterface;

    public TaskDTO createTask(String url){
        TaskDTO taskDTO = openFeignInterface.createTask(url);

        try {
            runTask(taskDTO);
        }
        catch (IOException e) {
            System.out.println("ERRO na chamada do runTask: " + e.getLocalizedMessage());
        }
        return taskDTO;
    }

    public TaskDTO getTask(int taskId){
        TaskDTO taskDTO = openFeignInterface.getTask(taskId);
        System.out.println("Tarefa obtida:" + taskDTO.toString());

        if ( taskDTO.getUrl() != null) {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(taskDTO.getUrl());
            String decodedURL = new String(decodedBytes);
            taskDTO.setUrl(decodedURL);
        }

        return taskDTO;
    }

    public void runTask (TaskDTO taskDTO) throws IOException {
        Process process = new ProcessBuilder("java", "-Dtaskid="+taskDTO.getTaskId(), "-Durl="+taskDTO.getUrl(), "-jar", "./LanguageIdentifier.jar").start();
        process.getInputStream().close();
    }
}