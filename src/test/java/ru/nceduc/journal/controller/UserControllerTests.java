package ru.nceduc.journal.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.nceduc.journal.controller.rest.UserController;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.service.AuthService;
import ru.nceduc.journal.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    private final String mapping = "/api/v1/user";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

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
    @WithMockUser(authorities = "USER")
    public void getAllUsers() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllUsersByAdmin() throws Exception {
        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(users.toArray())));
    }

    @Test
    @WithAnonymousUser
    public void createUser() throws Exception {
        Mockito.when(userService.create(firstUser)).thenReturn(Optional.of(firstUser));
        Mockito.when(userService.create(secondUser)).thenReturn(Optional.empty());

        mockMvc.perform(post(mapping + "/signup/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstUser)));

        mockMvc.perform(post(mapping + "/signup/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(secondUser)))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithAnonymousUser
    public void authenticate() throws Exception {
        Mockito.doThrow(new UsernameNotFoundException("User not found")).when(authService).authUser(secondUser);

        mockMvc.perform(post(mapping + "/signin/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstUser)))
                .andExpect(status().isOk());

        mockMvc.perform(post(mapping + "/signin/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(secondUser)))
                .andExpect(status().isForbidden());
    }
}