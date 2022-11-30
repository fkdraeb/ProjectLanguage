package com.upskill.LanguageIdentifier;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean("bean.porta")
    ServletWebServerApplicationContext getSocket(){
        return new ServletWebServerApplicationContext();
    }


}
