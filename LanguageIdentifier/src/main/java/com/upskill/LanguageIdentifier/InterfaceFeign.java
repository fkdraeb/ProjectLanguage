package com.upskill.LanguageIdentifier;

import com.upskill.LanguageIdentifier.entity.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="DATABASEMANAGER-SERVER")
public interface InterfaceFeign {

    @PostMapping(value="/finished-task", produces = "Application/json")
    public void sendFinishedTask(@RequestBody TaskDTO taskDTO);

}
