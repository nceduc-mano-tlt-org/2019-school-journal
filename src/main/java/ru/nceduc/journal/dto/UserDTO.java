package ru.nceduc.journal.dto;

import lombok.Data;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Role;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private boolean active;
    private Project project;
    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
