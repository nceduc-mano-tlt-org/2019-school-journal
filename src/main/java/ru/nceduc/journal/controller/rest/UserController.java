package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/signup/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Users operations", tags = "USER-V1")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Get all users") // TODO: move to secure location
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUser() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
    }
}
