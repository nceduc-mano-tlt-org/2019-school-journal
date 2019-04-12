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
import ru.nceduc.journal.controller.rest.StudentController;
import ru.nceduc.journal.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@WithMockUser(authorities = "USER")
public class StudentControllerTests {

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() {
    }

    @Test
    public void getAllStudents() {
    }

    @Test
    public void getStudent() {
    }

    @Test
    public void getAllStudentsByGroupId() {
    }

    @Test
    public void createStudent() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void patchStudent() {
    }

    @Test
    public void deleteStudent() {
    }
}