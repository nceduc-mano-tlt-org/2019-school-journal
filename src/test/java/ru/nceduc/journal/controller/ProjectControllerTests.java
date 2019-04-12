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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.nceduc.journal.controller.rest.ProjectController;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

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

    private ProjectDTO firstProject;
    private ProjectDTO secondProject;
    private List<ProjectDTO> currentProjects;
    private List<ProjectDTO> allProjects;

    @Before
    public void setUp() {
        firstProject = new ProjectDTO(UUID.randomUUID().toString(), "First", "First description", UUID.randomUUID().toString());
        secondProject = new ProjectDTO(UUID.randomUUID().toString(), "Second", "Second description", UUID.randomUUID().toString());

        currentProjects = new ArrayList<>();
        currentProjects.add(firstProject);
        allProjects = new ArrayList<>();
        allProjects.add(firstProject);
        allProjects.add(secondProject);
    }

    @Test
    public void getProject() throws Exception {
        String id = firstProject.getId();

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getProjectByAdmin() throws Exception {
        String id = firstProject.getId();
        Mockito.when(projectService.get(id)).thenReturn(Optional.of(firstProject));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstProject)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllProjects() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllProjectsByAdmin() throws Exception {
        Mockito.when(projectService.getAll()).thenReturn(allProjects);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(allProjects.toArray())));
    }

    @Test
    public void getCurrentProjects() throws Exception {
        Mockito.when(projectService.getAllByCurrentUser()).thenReturn(currentProjects);

        mockMvc.perform(get(mapping + "/current/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(currentProjects.toArray())));
    }

    @Test
    public void createProject() throws Exception {
        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstProject)))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void createProjectByAdmin() throws Exception {
        Mockito.when(projectService.create(firstProject)).thenReturn(Optional.of(firstProject));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstProject)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstProject)));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateProject() throws Exception {
        Mockito.when(projectService.update(firstProject)).thenReturn(Optional.of(firstProject));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstProject)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstProject)));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchProject() throws Exception {
        Mockito.when(projectService.patch(firstProject)).thenReturn(Optional.of(firstProject));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstProject)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstProject)));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteGroup() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void deleteGroupByAdmin() {
    }
}