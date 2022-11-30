package com.upskill.TaskManager;

import com.upskill.TaskManager.entity.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DATABASEMANAGER-SERVER")
public interface OpenFeignInterface {

    @GetMapping(value="/create-task/{url}", produces = "Application/json")
    public TaskDTO createTask(@PathVariable("url") String url);

    @GetMapping(value="/get-task/{taskid}", produces = "Application/json")
    public TaskDTO getTask(@PathVariable("taskid") int taskId);

}
