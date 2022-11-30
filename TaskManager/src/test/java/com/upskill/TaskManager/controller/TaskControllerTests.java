package com.upskill.TaskManager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TaskControllerTests {

    private TaskController taskController;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        taskController = new TaskController();
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void taskPageShouldBeLaunched() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-task.html"));
    }
}
