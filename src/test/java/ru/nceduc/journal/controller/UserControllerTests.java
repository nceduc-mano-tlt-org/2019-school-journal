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
import ru.nceduc.journal.service.AuthService;
import ru.nceduc.journal.service.UserService;

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

    @Before
    public void setUp() {
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