package ru.example.tild.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignup {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]{1,15}$", message = "Некорректное имя пользователя")
    private String name;
    @Pattern(regexp = "^[a-zA-Zа-яА-Я ]{0,15}$", message = "Некорректное имя пользователя")
    private String surname;
    @NotBlank(message = "Почта не может быть пустой")
    @Email(message = "Не верный почтовый адрес")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,32}$", message = "Некорректный пароль")
    private String password;

    @NotBlank(message = "Никнейм не может быть пустым")
    @Pattern(regexp = "^[a-zA-Z]{4,16}$", message = "Некорректный никнейм")
    private String nickName;

    public UserSignup(String name, String password, String surname, String nickName, String email){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

}
