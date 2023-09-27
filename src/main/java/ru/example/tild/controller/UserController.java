package ru.example.tild.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.tild.service.UserService;
import ru.example.tild.model.request.UserSignup;
import ru.example.tild.model.response.UserPreviewInfo;
import ru.example.tild.model.response.UserProfileData;

import java.util.HashSet;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@Tag(name="Пользователи", description="Взаимодействие с пользователем")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Зарегистрироваться", description = "Зарегистрироваться в проекте")
    @PostMapping(path = "/signup")
    public ResponseEntity<UserProfileData> signUpUser(@RequestBody UserSignup userSignup, HttpServletResponse response){
        response.getStatus();
        return userService.userSignUp(userSignup);
    }


    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получить всех юзеров", description = "Позволяет получить всех юзеров из БД")
    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<HashSet<UserPreviewInfo>> getAllUsers(){
        return userService.getAllUsers();
    }


    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получить профиль", description = "Позволяет получить данные профиля по id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserProfileData> getUserProfileById(@PathVariable Long id, HttpServletResponse response){
        response.getStatus();
        return userService.getUserProfileById(id);
    }


    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Привет)", description = "HELLO MIR MANERA")
    @GetMapping
    public ResponseEntity<String> helloUser(){
        return new ResponseEntity<>("Hello user", HttpStatus.OK);
    }

}
