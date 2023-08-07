package ru.example.tild.service;

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

    public ResponseEntity<TaskFullInfo> processAddTask(CreateTask createTask){
        Task task = new Task(createTask);
        Optional<User> author = userRepository.findById(createTask.getAuthorId());
        Optional<User> responsibleUser = userRepository.findById(createTask.getResponsibleUserId());
        Optional<Project> project = projectRepository.findById(createTask.getProjectId());

        if(!author.isPresent()){
            task.setAuthorId(null);
        }else {
            task.setAuthorId(author.get());
        }

        if(!responsibleUser.isPresent()){
            task.setResponsibleUserId(null);
        }else {
            task.setResponsibleUserId(responsibleUser.get());
        }

        if(!project.isPresent()){
            task.setProjectId(null);
        }else {
            task.setProjectId(project.get());
        }

        task = taskRepository.save(task);
        return new ResponseEntity<>(new TaskFullInfo(task), HttpStatus.OK);
    }

    public ResponseEntity<HashSet<TaskPreview>> processGetAllTasks(){
        HashSet<TaskPreview> tasks = new HashSet<>();
        taskRepository.findAll().forEach(task -> {
            tasks.add(new TaskPreview(task));
        });

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}