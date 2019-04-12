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
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.SectionService;

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

    @Before
    public void setUp() {

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
