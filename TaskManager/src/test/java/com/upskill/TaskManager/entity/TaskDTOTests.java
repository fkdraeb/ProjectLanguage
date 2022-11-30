package com.upskill.TaskManager.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDTOTests {

    TaskDTO taskDTO = new TaskDTO(1, "www.google.pt");

    TaskDTO taskDTO2 = new TaskDTO();

    @Test
    void isConstructorOk () {
        TaskDTO taskDTO3 = new TaskDTO(1, "www.google.pt");
        assertEquals(taskDTO, taskDTO3);
    }

}
