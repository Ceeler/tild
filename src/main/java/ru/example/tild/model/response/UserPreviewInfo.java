package ru.example.tild.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.tild.database.structure.User.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPreviewInfo {

    private String name;

    private String surname;

    private String status;

    private Long id;

    public UserPreviewInfo(User user){
        this.name = user.getName();
        this.surname = user.getSurname();
        this.status = user.getStatus();
        this.id = user.getId();
    }

}
