package ru.example.tild.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTask {

    private String taskName;

    private String taskDescription;

    private Long authorId;

    private Long projectId;

    private Set<Long> executorsId = new HashSet<>();

}
