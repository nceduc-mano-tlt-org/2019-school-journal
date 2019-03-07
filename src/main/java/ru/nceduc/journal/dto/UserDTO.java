package ru.nceduc.journal.dto;

import lombok.Data;
import ru.nceduc.journal.entity.Role;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private boolean active;
    private String projectId;
    private Set<Role> roles;
}
