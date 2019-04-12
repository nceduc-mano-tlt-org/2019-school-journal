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
import ru.nceduc.journal.controller.rest.StudentController;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@WithMockUser(authorities = "USER")
public class StudentControllerTests {
    private final String mapping = "/api/v1/student";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    private GroupDTO groupDTO;
    private StudentDTO firstStudent;
    private StudentDTO secondStudent;
    private StudentDTO thirdStudent;
    private List<StudentDTO> allStudents;
    private List<StudentDTO> studentsByGroup;

    @Before
    public void setUp() {
        String groupId = UUID.randomUUID().toString();

        groupDTO = new GroupDTO(groupId, "First group", "Desc for first group", null, 1000, UUID.randomUUID().toString());
        firstStudent = new StudentDTO(UUID.randomUUID().toString(), "Ivan", "Ivanov", groupId, null);
        secondStudent = new StudentDTO(UUID.randomUUID().toString(), "Petr", "Petrov", groupId, null);
        thirdStudent = new StudentDTO(UUID.randomUUID().toString(), "Alexey", "Frolov", UUID.randomUUID().toString(), null);

        allStudents = new ArrayList<>();
        allStudents.add(firstStudent);
        allStudents.add(secondStudent);
        allStudents.add(thirdStudent);

        studentsByGroup = new ArrayList<>();
        studentsByGroup.add(firstStudent);
        studentsByGroup.add(secondStudent);
    }

    @Test
    public void getStudent() throws Exception {
        String id = firstStudent.getId();
        Mockito.when(studentService.get(id)).thenReturn(Optional.of(firstStudent));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstStudent)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllStudents() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllStudentsByAdmin() throws Exception {
        Mockito.when(studentService.getAll()).thenReturn(allStudents);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(allStudents.toArray())));
    }

    @Test
    public void getAllStudentsByGroupId() throws Exception {
        String id = groupDTO.getId();
        Mockito.when(studentService.getAllByGroupId(id)).thenReturn(studentsByGroup);

        mockMvc.perform(get(mapping + "/by-group/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(studentsByGroup.toArray())));

        mockMvc.perform(get(mapping + "/by-group/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void createStudent() throws Exception {
        Mockito.when(studentService.create(firstStudent)).thenReturn(Optional.of(firstStudent));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstStudent)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstStudent)));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateStudent() throws Exception {
        Mockito.when(studentService.update(firstStudent)).thenReturn(Optional.of(firstStudent));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstStudent)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstStudent)));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchStudent() throws Exception {
        Mockito.when(studentService.patch(firstStudent)).thenReturn(Optional.of(firstStudent));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstStudent)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstStudent)));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStudent() {
    }
}