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
import ru.nceduc.journal.controller.rest.SectionController;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.SectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void getSection() {

    }

    @Test
    public void getAllSections() {

    }

    @Test
    public void getAllSectionByAdmin() {

    }

    @Test
    public void getAllSectionsByProjectId() {

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
