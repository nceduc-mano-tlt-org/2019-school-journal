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
import ru.nceduc.journal.controller.rest.TeacherController;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherController.class)
@WithMockUser(authorities = "USER")
public class TeacherControllerTests {
    private final String mapping = "/api/v1/teacher";

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
    public void getTeacher() throws Exception {
        String id = firstTeacher.getId();
        Mockito.when(teacherService.get(id)).thenReturn(Optional.of(firstTeacher));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstTeacher)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllTeachers() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllTeachersByAdmin() throws Exception {
        Mockito.when(teacherService.getAll()).thenReturn(allTeachers);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(allTeachers.toArray())));
    }

    @Test
    public void getAllTeachersByGroupId() throws Exception {
        String id = groupDTO.getId();
        Mockito.when(teacherService.getAllByGroupId(id)).thenReturn(teachersByGroup);

        mockMvc.perform(get(mapping + "/by-group/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(teachersByGroup.toArray())));

        mockMvc.perform(get(mapping + "/by-group/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void createTeacher() throws Exception {
        Mockito.when(teacherService.create(firstTeacher)).thenReturn(Optional.of(firstTeacher));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstTeacher)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstTeacher)));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTeacher() throws Exception {
        Mockito.when(teacherService.update(firstTeacher)).thenReturn(Optional.of(firstTeacher));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstTeacher)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstTeacher)));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchTeacher() throws Exception {
        Mockito.when(teacherService.patch(firstTeacher)).thenReturn(Optional.of(firstTeacher));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstTeacher)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstTeacher)));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteTeacher() {
    }
}