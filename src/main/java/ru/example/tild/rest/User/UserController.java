package ru.example.tild.rest.User;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.tild.model.request.UserSignUpRequest;
import ru.example.tild.model.response.UserProfileData;

@RestController("/user")
@AllArgsConstructor
public class UserController {

    private final UserResponseBuilder userResponseBuilder;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileData> signUpUser(@RequestBody UserSignUpRequest userSignUpRequest, HttpServletResponse response){
        return userResponseBuilder.processUserSignUp(userSignUpRequest);
    }
}
