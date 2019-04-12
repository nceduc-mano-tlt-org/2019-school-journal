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
import ru.nceduc.journal.controller.rest.SectionController;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.SectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(SectionController.class)
@WithMockUser(authorities = "USER")
public class SectionControllerTests {
    private final String mapping = "/api/v1/section";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private SectionService sectionService;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    private ProjectDTO projectDTO;
    private SectionDTO englishSection;
    private SectionDTO spanishSection;
    private List<SectionDTO> sections;

    @Before
    public void setUp() {
        String projectId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        projectDTO = new ProjectDTO(projectId, "Default", "Default Description", userId);
        englishSection = new SectionDTO(UUID.randomUUID().toString(), "English", "En description", projectId);
        spanishSection = new SectionDTO(UUID.randomUUID().toString(), "Spanish", "Sp description", projectId);

        sections = new ArrayList<>();
        sections.add(englishSection);
        sections.add(spanishSection);
    }

    @Test
    public void getSection() throws Exception {
        String id = englishSection.getId();
        Mockito.when(sectionService.get(id)).thenReturn(Optional.of(englishSection));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(englishSection)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllSections() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }


    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllSectionByAdmin() throws Exception {
        Mockito.when(sectionService.getAll()).thenReturn(sections);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(sections.toArray())));
    }

    @Test
    public void getAllSectionsByProjectId() throws Exception {
        String id = projectDTO.getId();
        Mockito.when(sectionService.getAllByProjectId(id)).thenReturn(sections);

        mockMvc.perform(get(mapping + "/by-project/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(sections.toArray())));

        mockMvc.perform(get(mapping + "/by-project/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void createSection() {

    }

    @Test
    public void updateSection() {

    }

    @Test
    public void patchSection() {

    }

    @Test
    public void deleteSection() {

    }

}
