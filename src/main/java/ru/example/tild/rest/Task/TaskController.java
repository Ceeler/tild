package ru.example.tild.rest.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.tild.model.request.AddTaskRequest;
import ru.example.tild.model.response.TaskFullInfo;

@RestController("task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskResponseBuilder taskResponseBuilder;
    @PostMapping(path = "/addTask")
    public ResponseEntity<TaskFullInfo> addTask(@RequestBody AddTaskRequest addTaskRequest){
        return taskResponseBuilder.processAddTask(addTaskRequest);
    }

}
