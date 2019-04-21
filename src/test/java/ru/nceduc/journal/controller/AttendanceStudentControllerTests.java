package ru.nceduc.journal.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.controller.rest.AttendanceStudentController;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AttendanceStudentController.class)
@WithMockUser(authorities = "USER")
public class AttendanceStudentControllerTests {
    private final String mapping = "/api/v1/student/attendance";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AttendanceStudentService attendanceStudentService;

    private AttendanceStudentDTO firstAttendanceStudent;
    private AttendanceStudentDTO secondAttendanceStudent;
    private List<AttendanceStudentDTO> attendances;

    @Before
    public void setUp() {
        String groupId = UUID.randomUUID().toString();
        firstAttendanceStudent = new AttendanceStudentDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                groupId, LocalDate.now());
        secondAttendanceStudent = new AttendanceStudentDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                groupId, LocalDate.now());

        attendances = new ArrayList<>();
        attendances.add(firstAttendanceStudent);
        attendances.add(secondAttendanceStudent);
    }

    @Test
    public void getAllByAttendanceByGroupId() {
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