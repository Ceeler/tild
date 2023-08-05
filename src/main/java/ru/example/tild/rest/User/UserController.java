package ru.example.tild.rest.User;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.tild.model.request.UserSignUpRequest;
import ru.example.tild.model.response.UserPreviewInfo;
import ru.example.tild.model.response.UserProfileData;

import java.util.HashSet;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class UserController {

    private final UserResponseBuilder userResponseBuilder;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserProfileData> signUpUser(@RequestBody UserSignUpRequest userSignUpRequest, HttpServletResponse response){
        return userResponseBuilder.processUserSignUp(userSignUpRequest);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<HashSet<UserPreviewInfo>> getAllUsers(){
        return userResponseBuilder.processGetAllUsers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserProfileData> getUserProfileById(@PathVariable Long id){
        return userResponseBuilder.processGetUserProfileById(id);
    }

    @GetMapping
    public ResponseEntity<String> helloUser(){
        return new ResponseEntity<>("Hello user", HttpStatus.OK);
    }

}
