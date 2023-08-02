package ru.example.tild.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.tild.database.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpRequest {

    private String name;

    private String surname;

    private String position;

    private String password;

    private String nickName;
    private UserRole userRole;

    UserSignUpRequest(String name, String password, String surname, String nickName, String position){
        this.name = name;
        this.surname = surname;
        this.userRole = UserRole.USER;
        this.password = password;
        this.position = position;
        this.nickName = nickName;
    }

}
