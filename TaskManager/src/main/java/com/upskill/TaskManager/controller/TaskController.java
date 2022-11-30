package com.upskill.TaskManager.controller;

import com.upskill.TaskManager.entity.TaskDTO;
import com.upskill.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Base64;


@RestController
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping("/")
    public ModelAndView launchTaskPage(ModelAndView modelAndView) {
        modelAndView.addObject("taskDTO", new TaskDTO());
        modelAndView.setViewName("create-task.html");
        return modelAndView;
    }


    @PostMapping("/addtask")
    public ModelAndView createTask(@ModelAttribute TaskDTO taskDTO, ModelAndView modelAndView) throws IOException {
        modelAndView.addObject("taskDTO", service.createTask(Base64.getUrlEncoder().encodeToString(taskDTO.getUrl().getBytes())));
        modelAndView.setViewName("create-task.html");
        return modelAndView;
    }

    @PostMapping("/viewtask")
    public ModelAndView viewTask(@ModelAttribute TaskDTO taskDTO, ModelAndView modelAndView) {
        modelAndView.addObject("taskDTO", service.getTask(taskDTO.getTaskId()));
        modelAndView.setViewName("view-task-sucess.html");
        return modelAndView;
    }


}
