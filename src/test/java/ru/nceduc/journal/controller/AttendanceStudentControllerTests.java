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
import ru.nceduc.journal.controller.rest.AttendanceStudentController;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

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
    private MockMvc mockMvc;

    private String groupId;
    private AttendanceStudentDTO firstAttendanceStudent;
    private AttendanceStudentDTO secondAttendanceStudent;
    private List<AttendanceStudentDTO> attendances;

    @Before
    public void setUp() {
        groupId = UUID.randomUUID().toString();
        firstAttendanceStudent = new AttendanceStudentDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                groupId, null);
        secondAttendanceStudent = new AttendanceStudentDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                groupId, null);

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
                .andExpect(content().json(toJson(attendances.toArray())));

        mockMvc.perform(get(mapping + "/by-group/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void deleteAttendanceStudent() {
    }

    @Test
    public void createAttendanceStudent() {
    }

    @Test
    public void getAttendanceStudent() {
    }

    @Test
    public void getAllByFilter() {
    }
}