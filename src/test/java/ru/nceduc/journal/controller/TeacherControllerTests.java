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
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private GroupDTO groupDTO;
    private TeacherDTO firstTeacher;
    private TeacherDTO secondTeacher;
    private TeacherDTO thirdTeacher;
    private List<TeacherDTO> allTeachers;
    private List<TeacherDTO> teachersByGroup;

    @Before
    public void setUp() {
        String groupId = UUID.randomUUID().toString();

        groupDTO = new GroupDTO(groupId, "First group", "Desc for first group", null, 1000, UUID.randomUUID().toString());
        firstTeacher = new TeacherDTO(UUID.randomUUID().toString(), "Elena", "Sidorova", groupId);
        secondTeacher = new TeacherDTO(UUID.randomUUID().toString(), "Egor", "Trofimov", groupId);
        thirdTeacher = new TeacherDTO(UUID.randomUUID().toString(), "Violetta", "Aleeva", UUID.randomUUID().toString());

        allTeachers = new ArrayList<>();
        allTeachers.add(firstTeacher);
        allTeachers.add(secondTeacher);
        allTeachers.add(thirdTeacher);

        teachersByGroup = new ArrayList<>();
        teachersByGroup.add(firstTeacher);
        teachersByGroup.add(secondTeacher);
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