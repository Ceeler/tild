package ru.example.tild.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignup {

    private String name;

    private String surname;

    private String email;

    private String password;

    private String nickName;

    public UserSignup(String name, String password, String surname, String nickName, String email){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

}
