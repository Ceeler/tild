package ru.example.tild.rest.Task;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.Project.ProjectRepository;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.Task.TaskRepository;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.database.structure.User.UserRepository;
import ru.example.tild.model.request.AddTaskRequest;
import ru.example.tild.model.response.TaskFullInfo;

import java.util.Optional;


@Service
@AllArgsConstructor
public class TaskResponseBuilder {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    ResponseEntity<TaskFullInfo> processAddTask(AddTaskRequest addTaskRequest){
        Task task = new Task(addTaskRequest);
        Optional<User> author = userRepository.findById(addTaskRequest.getAuthorId());
        Optional<User> responsibleUser = userRepository.findById(addTaskRequest.getResponsibleUserId());
        Optional<Project> project = projectRepository.findById(addTaskRequest.getProjectId());

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
}
