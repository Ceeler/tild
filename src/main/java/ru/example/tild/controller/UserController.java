package ru.example.tild.controller;

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
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserProfileData> signUpUser(@RequestBody UserSignup userSignup, HttpServletResponse response){
        response.getStatus();
        return userService.processUserSignUp(userSignup);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<HashSet<UserPreviewInfo>> getAllUsers(){
        return userService.processGetAllUsers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserProfileData> getUserProfileById(@PathVariable Long id, HttpServletResponse response){
        response.getStatus();
        return userService.processGetUserProfileById(id);
    }

    @GetMapping
    public ResponseEntity<String> helloUser(){
        return new ResponseEntity<>("Hello user", HttpStatus.OK);
    }

}
