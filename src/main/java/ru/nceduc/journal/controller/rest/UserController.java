package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.service.AuthService;
import ru.nceduc.journal.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Users operations", tags = "USER-V1")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @ApiOperation(value = "Get all users") // TODO: secure this
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUser() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup/")
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
    }

    @ApiOperation(value = "Authorize a new user")
    @PostMapping("/signin/")
    public void authenticate(@RequestBody UserDTO userDTO) {
        authService.authUser(userDTO);
    }
}
