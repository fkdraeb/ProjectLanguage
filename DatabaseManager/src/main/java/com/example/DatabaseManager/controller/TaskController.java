package com.example.DatabaseManager.controller;

import com.example.DatabaseManager.entity.Task;
import com.example.DatabaseManager.entity.TaskDTO;
import com.example.DatabaseManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


@RestController
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping(value = "/create-task/{url}", produces = "Application/json")
    public TaskDTO createTask(@PathVariable("url") String url){
        System.out.println("Chegou URL codificada ao createTask do DBM " + url);

        byte[] decodedBytes = Base64.getUrlDecoder().decode(url);
        String decodedURL = new String(decodedBytes);
        Task task = service.createTask(decodedURL);

        service.saveTask(task);

        return service.convertNewTaskToTaskDTO(task);
    }

    @PostMapping(value="/finished-task", produces = "Application/json")
    public void sendFinishedTask(@RequestBody TaskDTO taskDTO) {
        System.out.println("TaskDTO chegou ao DBM do LI via sendFinishedTask " + taskDTO.toString());
        service.saveTask(service.convertFinishedTaskDTOToTask(taskDTO));
    }

    @GetMapping(value="/get-task/{taskid}", produces = "Application/json")
    public TaskDTO getTask(@PathVariable("taskid") int taskId){
        return service.convertTaskToTaskDTO(service.findTaskById(taskId));
    }
}
