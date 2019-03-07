package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.UserEntity;

public interface UserService extends GenericService<UserDTO> {
    UserEntity getCurrentUsername();
    boolean getByName(String name);
}
