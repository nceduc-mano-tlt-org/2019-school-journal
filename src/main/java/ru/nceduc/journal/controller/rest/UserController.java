package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description="Users operations", tags = "USER-V1")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Create a new user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createUser(UserDTO userDTO) {
        userService.create(userDTO);
    }

}
