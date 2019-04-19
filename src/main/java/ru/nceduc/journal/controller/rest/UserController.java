package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.service.AuthService;
import ru.nceduc.journal.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to users", tags = "USER-V1")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @ApiOperation(value = "Get all users")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user")
    @PostMapping("/signup/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        Optional<UserDTO> optionalDTO = userService.create(userDTO);
        return optionalDTO.map(userDTO1 -> new ResponseEntity<>(userDTO1, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Authorize a new user")
    @PostMapping("/signin/")
    @ResponseStatus(HttpStatus.OK)
    public void authenticate(@RequestBody UserDTO userDTO) {
        authService.authUser(userDTO);
    }
}
