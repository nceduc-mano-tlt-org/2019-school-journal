package ru.nceduc.journal.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(UserDTO userDTO) {
        userService.create(userDTO);
    }

}
