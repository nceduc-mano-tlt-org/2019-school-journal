package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.UserDTO;

public interface AuthService {
    void authUser(UserDTO userDTO);
}
