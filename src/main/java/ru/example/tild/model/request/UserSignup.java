package ru.example.tild.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Сущность пользователя для решистрации")
public class UserSignup {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]{1,15}$", message = "Некорректное имя пользователя")
    @Schema(description = "Имя", example = "Данил")
    private String firstName;

    @Schema(description = "Фамилия", example = "Лосев")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я ]{0,15}$", message = "Некорректное имя пользователя")
    private String lastName;
    @Schema(description = "Почта", example = "losev@danil-m.ru")
    @NotBlank(message = "Почта не может быть пустой")
    @Email(message = "Не верный почтовый адрес")
    private String email;

    @Schema(description = "Логин", example = "Ceeler")
    @NotBlank(message = "Никнейм не может быть пустым")
    @Pattern(regexp = "^[a-zA-Z]{4,16}$", message = "Некорректный никнейм")
    private String username;

    @Schema(description = "Пароль", example = "1234qwer!@")
    @NotBlank(message = "Пароль не может быть пустым")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,32}$", message = "Некорректный пароль")
    private String password;

    public UserSignup(String firstName, String password, String lastName, String username, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.username = username;
    }
}