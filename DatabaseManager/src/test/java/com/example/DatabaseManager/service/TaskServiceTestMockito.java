package com.example.DatabaseManager.service;

import com.example.DatabaseManager.entity.Task;
import com.example.DatabaseManager.entity.TaskDTO;
import com.example.DatabaseManager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskServiceTestMockito {

    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository mockedRepo;

    Task task1 = new Task (2, "www.google.pt");

    @Test
    void findById() {
        doReturn(Optional.of(task1)).when(mockedRepo).findById(2);
        assertEquals(task1, taskService.findTaskById(2));
    }

    @Test
    void findById2() {
        doReturn(Optional.of(task1)).when(mockedRepo).findById(3);
        assertNotEquals(task1, taskService.findTaskById(2));
    }

    @Test
    public void save() {
        doReturn(task1).when(mockedRepo).save(task1);
        assertEquals(task1, taskService.saveTask(task1));
    }

    @Test
    void convertFinishedTaskDTOToTask1() {
        TaskDTO taskDTO = new TaskDTO(2, "www.google.pt", LocalDateTime.of(2019, 03, 28, 14, 33, 48, 640000), "cancelada");
        doReturn(Optional.of(taskDTO)).when(mockedRepo).findById(2);

        task1.setInitialDate(taskDTO.getInitialDate());
        doReturn(Optional.of(task1)).when(mockedRepo).findById(2);

        taskService.convertFinishedTaskDTOToTask(taskDTO);

        assertEquals(task1.getTaskId(), taskDTO.getTaskId());
        assertEquals(task1.getInitialDate(), taskDTO.getInitialDate());
        assertEquals(task1.getStatus(), taskDTO.getStatus());
        assertEquals(task1.getCancelationCause(), taskDTO.getCancelationCause());

    }

    @Test
    void convertFinishedTaskDTOToTask2() {
        TaskDTO taskDTO = new TaskDTO(2, "www.google.pt", LocalDateTime.of(2019, 03, 28, 14, 33, 48, 640000), "concluída");
        doReturn(Optional.of(taskDTO)).when(mockedRepo).findById(2);

        task1.setInitialDate(taskDTO.getInitialDate());
        doReturn(Optional.of(task1)).when(mockedRepo).findById(2);

        taskService.convertFinishedTaskDTOToTask(taskDTO);

        assertEquals(task1.getTaskId(), taskDTO.getTaskId());
        assertEquals(task1.getInitialDate(), taskDTO.getInitialDate());
        assertEquals(task1.getStatus(), taskDTO.getStatus());
        assertEquals(task1.getLanguagePT(), taskDTO.getLanguagePT());
        assertEquals(task1.getLanguageCode(), taskDTO.getLanguageCode());
    }

    @Test
    void convertFinishedTaskDTOToTask3() {
        TaskDTO taskDTO = new TaskDTO(2, "www.google.pt", LocalDateTime.of(2019, 03, 28, 14, 33, 48, 640000), "em processamento");
        doReturn(Optional.of(taskDTO)).when(mockedRepo).findById(2);

        task1.setInitialDate(taskDTO.getInitialDate());
        doReturn(Optional.of(task1)).when(mockedRepo).findById(2);

        taskService.convertFinishedTaskDTOToTask(taskDTO);

        assertEquals(task1.getTaskId(), taskDTO.getTaskId());
        assertEquals(task1.getInitialDate(), taskDTO.getInitialDate());
        assertNotEquals(task1.getStatus(), taskDTO.getStatus());
        assertEquals(task1.getStatus(), "cancelada");
        assertEquals(task1.getCancelationCause(), "O programa não conseguiu executar a tarefa.");
    }


}
