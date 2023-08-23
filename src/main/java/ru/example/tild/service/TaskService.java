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

    public ResponseEntity<TaskFullInfo> processAddTask(CreateTask createTask){
        Task task = new Task(createTask);
        boolean isAuthorExist = userRepository.existsById(createTask.getAuthorId());
        boolean isResponsibleUserExist = userRepository.existsById(createTask.getResponsibleUserId());
        Optional<Project> project = projectRepository.findById(createTask.getProjectId());

        if(!isAuthorExist){
            throw new NotFoundException(HttpStatus.NOT_FOUND, "ID автора не найден");
        }
        if(!isResponsibleUserExist){
            throw new NotFoundException(HttpStatus.NOT_FOUND, "ID выполняющего не найден");
        }

        User respUser = new User();
        respUser.setId(createTask.getResponsibleUserId());
        task.setResponsibleUserId(respUser);
        User author = new User();
        author.setId(createTask.getAuthorId());
        task.setAuthorId(author);

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
