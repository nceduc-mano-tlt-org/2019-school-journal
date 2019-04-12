package ru.nceduc.journal.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.nceduc.journal.controller.rest.GroupController;
import ru.nceduc.journal.service.GroupService;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
@WithMockUser(authorities = "USER")
public class GroupControllerTests {
    private final String mapping = "/api/v1/group";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private GroupService groupService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
    }

    @Test
    public void getGroup() {
    }

    @Test
    public void getAllGroups() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllGroupsByAdmin() {

    }

    @Test
    public void getGroupsBySectionId() {
    }

    @Test
    public void createGroup() {
    }

    @Test
    public void updateGroup() {
    }

    @Test
    public void patchGroup() {
    }

    @Test
    public void deleteGroup() {
    }
}