package com.example.DatabaseManager.service;

import com.example.DatabaseManager.entity.Task;
import com.example.DatabaseManager.entity.TaskDTO;
import com.example.DatabaseManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository repo;

    public TaskDTO convertNewTaskToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO(task.getTaskId(), task.getUrl(), task.getInitialDate(), task.getStatus());
        encodeUrl(taskDTO);
        return taskDTO;
    }

    public TaskDTO convertTaskToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO(task.getTaskId(), task.getUrl(), task.getInitialDate(), task.getFinalDate(), task.getStatus(), task.getLanguageCode(), task.getLanguagePT(), task.getCancelationCause());
        encodeUrl(taskDTO);
        return taskDTO;
    }

    public void encodeUrl(TaskDTO taskDTO) {
        if (taskDTO.getUrl() != null){
            String encodedUrl = Base64.getUrlEncoder().encodeToString(taskDTO.getUrl().getBytes());
            taskDTO.setUrl(encodedUrl);
        }
    }

    public Task convertFinishedTaskDTOToTask(TaskDTO taskDTO){
        Task task = findTaskById(taskDTO.getTaskId());
        task.setStatus(taskDTO.getStatus());
        switch (task.getStatus().toLowerCase()) {
            case "cancelada":
                task.setCancelationCause(taskDTO.getCancelationCause());
                break;
            case "concluída":
                task.setLanguageCode(taskDTO.getLanguageCode());
                task.setLanguagePT(taskDTO.getLanguagePT());
                break;
            case "em processamento":
                task.setStatus("cancelada");
                task.setCancelationCause("O programa não conseguiu executar a tarefa.");
                break;
        }
        task.setFinalDate(LocalDateTime.now());
        return task;
    }

    public Task createTask(String url){
        Task task = new Task(url);
        task.setInitialDate(LocalDateTime.now());
        task.setStatus("Em processamento");
        return task;
    }

    public Task findTaskById(int id) {
        Optional<Task> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return new Task();
    }

    public Task saveTask(Task task) {
        return repo.save(task);
    }


}