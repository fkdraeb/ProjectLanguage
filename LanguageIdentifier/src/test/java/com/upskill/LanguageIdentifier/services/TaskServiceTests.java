package com.upskill.LanguageIdentifier.services;

import com.upskill.LanguageIdentifier.InterfaceFeign;
import com.upskill.LanguageIdentifier.entity.TaskDTO;
import com.upskill.LanguageIdentifier.service.TaskService;
import org.apache.tika.exception.TikaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ActiveProfiles("test")
@SpringBootTest
public class TaskServiceTests {


    TaskDTO taskTest1 = new TaskDTO(22, "https://www.theguardian.com/environment/2022/nov/01/climate-crisis-us-food-system-five-crops");
    TaskService taskServiceTest1 = new TaskService();

    @InjectMocks
    TaskService taskServiceTest1Mocked;

    @Mock
    InterfaceFeign interfaceFeignMock;

    @Mock
    TaskService mockedTaskService;

    private MockMvc mockMvc;
    @BeforeEach
    void setup() {
        this.mockMvc = standaloneSetup(new TaskService()).build();
    }


    @Test
    void isLanguageProperlyIdentified() {

        String languageIdentified;
        String expectedResult = "en";
        //
        try {
            languageIdentified = taskServiceTest1.identifyLanguage(taskTest1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TikaException e) {
            throw new RuntimeException(e);
        }
        //
        boolean languageIdentifiedBoolean = languageIdentified.equals(expectedResult);
        assertThat(languageIdentifiedBoolean).isTrue();

    }

    @Test
    void isTaskUpdatingToCancelled() {
        String status = "cancelada";
        String cause = "inválida";
        taskServiceTest1.updateStatus(taskTest1, status, cause);


        Boolean cancelledResult = taskTest1.getStatus().equals(status);
        String actualCancellationCause = taskTest1.getCancelationCause();

        assertThat(cancelledResult).isTrue();
        assertThat(actualCancellationCause).isEqualTo(cause);
    }

    @Test
    void isTaskUpdating() {
        String status = "concluído";
        //Date = ;

        taskServiceTest1.updateStatus(taskTest1, status);

        Boolean statusResult = taskTest1.getStatus().equals(status);

        assertThat(statusResult).isTrue();

    }

    @Test
    void isLanguageBeingAssigned() throws TikaException, IOException {


        String expectedResultCode = "en";
        String expectedResultPT = "Inglês";

        taskServiceTest1.setLanguage(taskTest1);

        assertThat(taskTest1.getLanguageCode().toString()).isEqualTo(expectedResultCode);
        assertThat(taskTest1.getLanguagePT()).isEqualTo(expectedResultPT);

    }
    @Test
    void isItCatchingIOExceptionWhenRunning(){
        TaskDTO mytaskDTO = new TaskDTO(1, "");
        assertThrows(
                Exception.class,
                () -> taskServiceTest1.runTask(mytaskDTO));

        assertThat(mytaskDTO.getCancelationCause()).isEqualTo("Não foi possivel aceder ao ficheiro porque o caminho introduzido está errado ou o ficheiro é ilegível.");

    }
    @Test
    void isItCatchingEmptyFileExceptionWhenRunning(){
        TaskDTO mytaskDTO = new TaskDTO(1, "https://www.twitch.tv/");

       assertThrows(
               Exception.class,
                () -> taskServiceTest1.runTask(mytaskDTO));

        assertThat(mytaskDTO.getCancelationCause()).isEqualTo(  "O ficheiro não contém texto.");

    }

    @Test
    void isItRunning() throws Exception {
        taskServiceTest1Mocked.runTask(taskTest1);

        Boolean boolStatus= taskTest1.getStatus().equals("Concluída");


        assertThat(taskTest1.getCancelationCause()).isNull();
        assertThat(boolStatus).isTrue();
    }
}
