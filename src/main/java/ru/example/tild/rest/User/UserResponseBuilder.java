package ru.example.tild.rest.User;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.database.structure.User.UserRepository;
import ru.example.tild.model.request.UserSignUpRequest;
import ru.example.tild.model.response.UserPreviewInfo;
import ru.example.tild.model.response.UserProfileData;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserResponseBuilder {

    private final UserRepository userRepository;
    ResponseEntity<UserProfileData> processUserSignUp(UserSignUpRequest userSignUpRequest){
        User user = new User(userSignUpRequest);
        user = userRepository.save(user);
        return new ResponseEntity<>(new UserProfileData(user), HttpStatus.OK);
    }

    ResponseEntity<HashSet<UserPreviewInfo>> processGetAllUsers(){
        HashSet<UserPreviewInfo> usersPreviews = new HashSet<>();
        userRepository.findAll().forEach(user -> {
            usersPreviews.add(new UserPreviewInfo(user));
        });
        return new ResponseEntity<>(usersPreviews, HttpStatus.OK);
    }

    ResponseEntity<UserProfileData> processGetUserProfileById(Long id){
        User user;
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            user = new User();
        }else {
            user = userOptional.get();
        }
        return new ResponseEntity<>(new UserProfileData(user), HttpStatus.OK);
    }
}
