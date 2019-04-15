package ru.nceduc.journal.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.controller.rest.UserController;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.service.AuthService;
import ru.nceduc.journal.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@WithMockUser(authorities = "USER")
public class UserControllerTests {
    private final String mapping = "api/v1/user";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    private UserDTO firstUser;
    private UserDTO secondUser;
    private List<UserDTO> users;

    @Before
    public void setUp() {
        firstUser = new UserDTO(UUID.randomUUID().toString(), "firstUser", "fPass3344", UUID.randomUUID().toString());
        secondUser = new UserDTO(UUID.randomUUID().toString(), "secondUser", "sPass3344", UUID.randomUUID().toString());

        users = new ArrayList<>();
        users.add(firstUser);
        users.add(secondUser);
    }

    @Test
    public void getAllUsers() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllUsersByAdmin() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void authenticate() {
    }
}