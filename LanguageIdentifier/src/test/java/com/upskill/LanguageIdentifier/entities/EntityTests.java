package com.upskill.LanguageIdentifier.entities;


import com.upskill.LanguageIdentifier.entity.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class EntityTests {
    TaskDTO taskTest1 = new TaskDTO(22, "https://www.theguardian.com/environment/2022/nov/01/climate-crisis-us-food-system-five-crops");
    TaskDTO TaskTest2 = new TaskDTO();


    @Test
    void isLanguageCodeBeingSet() {
        Locale langCodeTest = new Locale("es");
        taskTest1.setLanguageCode(langCodeTest);
        assertThat(taskTest1.getLanguageCode().toString()).isEqualTo("es");
    }

    @Test
    void isLanguageSetToPT() {
        String languagePTTest = "Espanhol";

        taskTest1.setLanguagePT(languagePTTest);

        assertThat(taskTest1.getLanguagePT()).isEqualTo("Espanhol");

    }

}
