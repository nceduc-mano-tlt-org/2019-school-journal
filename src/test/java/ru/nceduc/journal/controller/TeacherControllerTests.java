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
import ru.nceduc.journal.controller.rest.TeacherController;
import ru.nceduc.journal.service.TeacherService;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherController.class)
@WithMockUser(authorities = "USER")
public class TeacherControllerTests {

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
    }

    @Test
    public void getTeacher() {
    }

    @Test
    public void getAllTeachers() {
    }

    @Test
    public void getAllTeachersByAdmin() {
    }

    @Test
    public void getAllTeachersByGroupId() {
    }

    @Test
    public void createTeacher() {
    }

    @Test
    public void updateTeacher() {
    }

    @Test
    public void patchTeacher() {
    }

    @Test
    public void deleteTeacher() {
    }
}