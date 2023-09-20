package ru.example.tild.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.tild.model.request.CreateTask;
import ru.example.tild.model.response.TaskFullInfo;
import ru.example.tild.model.response.TaskPreview;
import ru.example.tild.service.TaskService;

import java.util.HashSet;

@RestController
@RequestMapping(path = "task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @PostMapping(path = "/create")
    public ResponseEntity<TaskFullInfo> addTask(@RequestBody CreateTask createTask){
        return taskService.addTask(createTask);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<HashSet<TaskPreview>> getAllTasks(){
        return taskService.getAllTasks();
    }

}
