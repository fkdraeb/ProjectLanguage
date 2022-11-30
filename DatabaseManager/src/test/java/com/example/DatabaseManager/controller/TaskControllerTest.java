package com.example.DatabaseManager.controller;

import com.example.DatabaseManager.entity.Task;
import com.example.DatabaseManager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shoudlCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldCreateTask() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/create-task/aHR0cHM6Ly9zcHJpbmcuaW8ueHkyNDAxLmNvbS9ndWlkZXMvZ3MvdGVzdGluZy13ZWIv"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldSendTask() throws Exception {
        Task task = new Task();
        task.setTaskId(111);
        when(taskService.findTaskById(111)).thenReturn(task);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-task/111"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
