package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.UserEntity;

import java.util.List;

public interface UserService extends GenericService<UserDTO> {
    List<UserDTO> findAllByProject(String projectId);
    UserEntity getCurrentUsername();
    boolean getByName(String name);
}
