package com.example.springboot.demo.controller;


import com.example.springboot.demo.model.Tasks;
import com.example.springboot.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DemoController {

    @Autowired
    private TaskRepository taskRepository;

    @Value("${ENV_VALUE}")
    private String envValue;

    @RequestMapping("/")
    public String index() {
        return "Welcome to " + envValue + "  environment";
    }


    @GetMapping("/greet/{name}")
    public String greeting(@PathVariable String name) {
        String env = "local";
        return "Welcome" + name +" to " + env + "  environment.";
    }

    @GetMapping("/tasks")
    public @ResponseBody
    Iterable<Tasks> getTasks() {
        // This returns a JSON or XML with the users
        return taskRepository.findAll();
    }

}


