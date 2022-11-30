package com.upskill.TaskManager.service;

import com.upskill.TaskManager.OpenFeignInterface;
import com.upskill.TaskManager.entity.TaskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskServiceTests {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private OpenFeignInterface mockedFeign;

    @Mock
    private TaskService taskService1;

    TaskDTO taskDTO = new TaskDTO(1, "www.google.pt");

    @Test
    void createTask () {
        String url = "www.google.pt";
        doReturn(taskDTO).when(mockedFeign).createTask(url);

        assertEquals(taskDTO, taskService.createTask(url));
    }

    @Test
    void getTask () {
        TaskDTO taskDTO1 = new TaskDTO(2, "aHR0cHM6Ly93ZWIud2hhdHNhcHAuY29tLw");
        doReturn(taskDTO1).when(mockedFeign).getTask(2);

        assertEquals(taskDTO1, taskService.getTask(2));
    }

    @Test
    void runTask () throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-Dtaskid="+taskDTO.getTaskId(), "-Durl="+taskDTO.getUrl(), "-jar", "../LanguageIdentifier.jar");
        processBuilder.redirectErrorStream(true);
        File log = new File("microservice.log");

        processBuilder.redirectErrorStream();
        BufferedReader fin =  new BufferedReader( new FileReader("microservice.log") );
        String line = null;
        boolean started = false;
        while ( (line = fin.readLine()) != null) {
            if (line.contains("Started LanguageIdentifier"));
            started = true;
        }

        taskService.runTask(taskDTO);
        assertTrue(started);
    }
}
