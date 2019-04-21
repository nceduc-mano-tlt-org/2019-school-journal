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
import ru.nceduc.journal.controller.rest.AttendanceFilterController;
import ru.nceduc.journal.controller.rest.AttendanceStudentController;
import ru.nceduc.journal.dto.AttendanceFilterDTO;
import ru.nceduc.journal.service.AttendanceFilterService;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AttendanceFilterController.class)
@WithMockUser(authorities = "USER")
public class AttendanceFilterControllerTests {
    private final String mapping = "/api/v1/attendance/filter";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AttendanceFilterService attendanceFilterService;

    @Autowired
    private MockMvc mockMvc;

    private AttendanceFilterDTO firstFilter;
    private AttendanceFilterDTO secondFilter;
    private AttendanceFilterDTO thirdFilter;
    private List<AttendanceFilterDTO> allFilters;
    private List<AttendanceFilterDTO> filtersByGroup;

    @Before
    public void setUp() throws Exception {
        String groupId = UUID.randomUUID().toString();
        firstFilter = new AttendanceFilterDTO(UUID.randomUUID().toString(), groupId, 10, 2020);
        secondFilter = new AttendanceFilterDTO(UUID.randomUUID().toString(), groupId, 11, 2020);
        thirdFilter = new AttendanceFilterDTO(UUID.randomUUID().toString(), groupId, 11, 2020);

        allFilters = new ArrayList<>();
        allFilters.add(firstFilter);
        allFilters.add(secondFilter);
        allFilters.add(thirdFilter);

        filtersByGroup = new ArrayList<>();
        filtersByGroup.add(firstFilter);
        filtersByGroup.add(secondFilter);
    }

    @Test
    public void getFilter() {
    }

    @Test
    public void getAllFilters() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllFiltersByAdmin() {
    }

    @Test
    public void getAllAttendancesByGroupId() {
    }

    @Test
    public void createFilter() {
    }

    @Test
    public void deleteFilter() {
    }
}