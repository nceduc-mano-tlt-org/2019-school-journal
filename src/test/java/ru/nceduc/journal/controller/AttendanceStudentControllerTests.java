package ru.nceduc.journal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.nceduc.journal.controller.rest.AttendanceStudentController;
import ru.nceduc.journal.dto.AttendanceFilterDTO;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AttendanceStudentController.class)
@WithMockUser(authorities = "USER")
public class AttendanceStudentControllerTests {
    private final String mapping = "/api/v1/attendance/student";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AttendanceStudentService attendanceStudentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private String groupId;
    private AttendanceStudentDTO firstAttendanceStudent;
    private AttendanceStudentDTO secondAttendanceStudent;
    private AttendanceFilterDTO attendanceFilterDTO;
    private List<AttendanceStudentDTO> attendances;

    @Before
    public void setUp() {
        groupId = UUID.randomUUID().toString();
        firstAttendanceStudent = new AttendanceStudentDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                groupId, LocalDate.now());
        secondAttendanceStudent = new AttendanceStudentDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                groupId, LocalDate.now());
        attendanceFilterDTO = new AttendanceFilterDTO(UUID.randomUUID().toString(), groupId, 10, 2020);

        attendances = new ArrayList<>();
        attendances.add(firstAttendanceStudent);
        attendances.add(secondAttendanceStudent);
    }

    @Test
    public void getAllByAttendanceByGroupId() throws Exception {
        Mockito.when(attendanceStudentService.getAllByGroupId(groupId)).thenReturn(attendances);

        mockMvc.perform(get(mapping + "/by-group/" + groupId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(attendances.toArray())));

        mockMvc.perform(get(mapping + "/by-group/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void getAllByFilter() throws Exception {
        String id = attendanceFilterDTO.getId();
        Mockito.when(attendanceStudentService.getAllByFilterId(id)).thenReturn(attendances);

        mockMvc.perform(get(mapping + "/by-filter/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(attendances.toArray())));

        mockMvc.perform(get(mapping + "/by-filter/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void getAttendanceStudent() throws Exception {
        String id = firstAttendanceStudent.getId();
        Mockito.when(attendanceStudentService.get(id)).thenReturn(Optional.of(firstAttendanceStudent));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(firstAttendanceStudent)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAttendanceStudent() throws Exception {
        Mockito.when(attendanceStudentService.create(firstAttendanceStudent)).thenReturn(Optional.of(firstAttendanceStudent));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstAttendanceStudent)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(firstAttendanceStudent)));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAttendanceStudent() throws Exception {
        String id = firstAttendanceStudent.getId();

        mockMvc.perform(delete(mapping + "/" + id))
                .andExpect(status().isNoContent());
    }
}