package ru.example.tild.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.Project.ProjectRepository;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.Task.TaskRepository;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.database.structure.User.UserRepository;
import ru.example.tild.exception.NotFoundException;
import ru.example.tild.model.request.CreateTask;
import ru.example.tild.model.response.TaskFullInfo;
import ru.example.tild.model.response.TaskPreview;

import java.util.HashSet;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public ResponseEntity<TaskFullInfo> addTask(CreateTask createTask){
        Task task = new Task(createTask);
        for(Long executorId : createTask.getExecutorsId()){
            task.addExecutor(userRepository.getReferenceById(executorId));
        }
        task.setAuthor(userRepository.getReferenceById(createTask.getAuthorId()));
        task.setProject(projectRepository.getReferenceById(createTask.getProjectId()));
        return new ResponseEntity<>(new TaskFullInfo(task), HttpStatus.OK);
    }

    public ResponseEntity<HashSet<TaskPreview>> getAllTasks(){
        HashSet<TaskPreview> tasks = new HashSet<>();
        taskRepository.findAll().forEach(task -> {
            tasks.add(new TaskPreview(task));
        });

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
