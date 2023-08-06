package ru.example.tild.rest.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.tild.model.request.AddTaskRequest;
import ru.example.tild.model.response.TaskFullInfo;
import ru.example.tild.model.response.TaskPreview;

@RestController
@RequestMapping(path = "task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskResponseBuilder taskResponseBuilder;
    @PostMapping(path = "/addTask")
    public ResponseEntity<TaskFullInfo> addTask(@RequestBody AddTaskRequest addTaskRequest){
        return taskResponseBuilder.processAddTask(addTaskRequest);
    }

    @GetMapping(path = "/getAllTasks")
    public ResponseEntity<TaskPreview> getAllTasks(){
        return null;
    }

}
