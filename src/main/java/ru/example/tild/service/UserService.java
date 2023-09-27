package ru.example.tild.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.database.structure.User.UserRepository;
import ru.example.tild.model.request.UserSignup;
import ru.example.tild.model.response.UserPreviewInfo;
import ru.example.tild.model.response.UserProfileData;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<UserProfileData> userSignUp(UserSignup userSignup){
        User user = new User(userSignup);
        user.setPassword(passwordEncoder.encode(userSignup.getPassword()));
        user = userRepository.save(user);
        return new ResponseEntity<>(new UserProfileData(user), HttpStatus.OK);
    }

    public ResponseEntity<HashSet<UserPreviewInfo>> getAllUsers(){
        HashSet<UserPreviewInfo> usersPreviews = new HashSet<>();
        userRepository.findAll().forEach(user -> {
            usersPreviews.add(new UserPreviewInfo(user));
        });
        return new ResponseEntity<>(usersPreviews, HttpStatus.OK);
    }

    public ResponseEntity<UserProfileData> getUserProfileById(Long id){
        User user;
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            user = new User();
        }else {
            user = userOptional.get();
        }
        return new ResponseEntity<>(new UserProfileData(user), HttpStatus.OK);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
