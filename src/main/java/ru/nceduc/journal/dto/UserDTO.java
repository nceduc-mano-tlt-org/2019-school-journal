package ru.nceduc.journal.dto;

import lombok.Data;
import ru.nceduc.journal.entity.Role;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String projectId;
}
