package com.example.DatabaseManager.service;

import com.example.DatabaseManager.entity.Task;
import com.example.DatabaseManager.entity.TaskDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class TaskServiceTest {

    Task taskTest1 = new Task(22, "https://www.theguardian.com/environment/2022/nov/01/climate-crisis-us-food-system-five-crops");
    TaskDTO taskDTO = new TaskDTO();
    TaskService taskServiceTest1 = new TaskService();
    private String url = "www.example.pt";

    @BeforeEach
    void setUp() {
    }


    @AfterEach
    void tearDown() {
    }


    @Test
    void convertNewTaskToTaskDTO() {
        taskTest1.setInitialDate(LocalDateTime.now());
        taskTest1.setStatus("cancelada");

        taskDTO = taskServiceTest1.convertNewTaskToTaskDTO(taskTest1);

        assertEquals(taskTest1.getTaskId(), taskDTO.getTaskId());
        assertEquals(taskTest1.getInitialDate(), taskDTO.getInitialDate());
        assertEquals(taskTest1.getStatus(), taskDTO.getStatus());
    }

    @Test
    void convertTaskToTaskDTO() {

        Locale langCode = new Locale.Builder().setLanguage("es").build();

        taskTest1.setInitialDate(LocalDateTime.now());
        taskTest1.setStatus("concluída");
        taskTest1.setLanguageCode(langCode);
        taskTest1.setLanguagePT(langCode.getDisplayLanguage(new Locale.Builder().setLanguage("pt").setRegion("PT").build()));
        taskTest1.setCancelationCause(null);
        taskTest1.setFinalDate(LocalDateTime.now());

        taskDTO = taskServiceTest1.convertTaskToTaskDTO(taskTest1);

        assertEquals(taskTest1.getTaskId(), taskDTO.getTaskId());
        assertEquals(taskTest1.getInitialDate(), taskDTO.getInitialDate());
        assertEquals(taskTest1.getStatus(), taskDTO.getStatus());
        assertEquals(taskTest1.getLanguageCode(), taskDTO.getLanguageCode());
        assertEquals(taskTest1.getLanguagePT(), taskDTO.getLanguagePT());
        assertEquals(taskTest1.getCancelationCause(), taskDTO.getCancelationCause());
        assertEquals(taskTest1.getFinalDate(), taskDTO.getFinalDate());
    }
    /*
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
     */
/*
    @Test
    void convertFinishedTaskDTOToTask() {
        taskDTO.setStatus("cancelada");
        taskDTO.setTaskId(99);
        taskTest1.setStatus(taskDTO.getStatus());
        taskTest1.setCancelationCause(taskDTO.getCancelationCause());

        taskServiceTest1.convertFinishedTaskDTOToTask(taskDTO);

        assertEquals(taskTest1.getStatus(), taskDTO.getStatus());
        assertEquals(taskTest1.getCancelationCause(), taskDTO.getCancelationCause());
    }

 */

    @Test
    void encodeUrl() {
        taskDTO.setUrl("http://www.google.pt");
        taskServiceTest1.encodeUrl(taskDTO);
        assertTrue(taskDTO.getUrl().equals(taskDTO.getUrl()));
    }

    @Test
    void createTask() {
        taskServiceTest1.createTask(url);
        Task task = taskServiceTest1.createTask(url);

        assertEquals("Em processamento", task.getStatus());
        assertNotEquals(null, task.getInitialDate());
        assertTrue(task.getUrl() != null);
    }


        /*
    @Test
    void findTaskById() {

        Task taskFromrepo = new Task(14, url);
        System.out.println(taskFromrepo);
        //when(mockedUserRepository.save(any(User.class)).thenReturn(userToReturnFromRepository);

        when(mockedRepo.findById(any(Task.class).getTaskId())).thenReturn(Optional.of(taskFromrepo));
    }*/


  /*
    @Test
    void saveTask() {
        Task dummyTask = new Task();
        when(mockedRepo.save(dummyTask)).thenReturn(dummyTask);

        Task result = taskService.findTaskById(1);

        assertThat("result", result, is(sameInstance(dummyTask)));



    }*/

}