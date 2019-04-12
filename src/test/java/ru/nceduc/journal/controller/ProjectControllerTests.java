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
import ru.nceduc.journal.controller.rest.ProjectController;
import ru.nceduc.journal.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
@WithMockUser(authorities = "USER")
public class ProjectControllerTests {
    private final String mapping = "/api/v1/project";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
    }

    @Test
    public void getProject() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getProjectByAdmin() {
    }

    @Test
    public void getAllProjects() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllProjectsByAdmin() {
    }

    @Test
    public void getCurrentProjects() {
    }

    @Test
    public void createProject() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void createProjectByAdmin() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void patchProject() {
    }


    @Test
    public void deleteGroup() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void deleteGroupByAdmin() {
    }
}