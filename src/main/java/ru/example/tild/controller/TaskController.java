package ru.example.tild.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Добавить задачу в проект", description = "Позволяет добавить новую задачу в проекте")
    @PostMapping(path = "/create")
    public ResponseEntity<TaskFullInfo> addTask(@RequestBody CreateTask createTask,
                                                @AuthenticationPrincipal UserDetails userDetails){
        return taskService.addTask(createTask, userDetails);
    }


    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получить все возможные задачи", description = "Позволяет получить все задачи из БД")
    @GetMapping(path = "/getAll")
    public ResponseEntity<HashSet<TaskPreview>> getAllTasks(){
        return taskService.getAllTasks();
    }

}
