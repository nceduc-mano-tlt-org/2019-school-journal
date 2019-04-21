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
import ru.nceduc.journal.controller.rest.AttendanceFilterController;
import ru.nceduc.journal.dto.AttendanceFilterDTO;
import ru.nceduc.journal.service.AttendanceFilterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

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

    private String groupId;
    private AttendanceFilterDTO firstFilter;
    private AttendanceFilterDTO secondFilter;
    private AttendanceFilterDTO thirdFilter;
    private List<AttendanceFilterDTO> allFilters;
    private List<AttendanceFilterDTO> filtersByGroup;

    @Before
    public void setUp() {
        groupId = UUID.randomUUID().toString();
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
    public void getFilter() throws Exception {
        String id = firstFilter.getId();
        Mockito.when(attendanceFilterService.get(id)).thenReturn(Optional.of(firstFilter));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstFilter)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllFilters() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllFiltersByAdmin() throws Exception {
        Mockito.when(attendanceFilterService.getAll()).thenReturn(allFilters);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(allFilters.toArray())));
    }

    @Test
    public void getAllFiltersByGroupId() throws Exception {
        Mockito.when(attendanceFilterService.getAllByGroupId(groupId)).thenReturn(filtersByGroup);

        mockMvc.perform(get(mapping + "/by-group/" + groupId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(filtersByGroup.toArray())));

        mockMvc.perform(get(mapping + "/by-group/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void createFilter() throws Exception {
        Mockito.when(attendanceFilterService.create(firstFilter)).thenReturn(Optional.of(firstFilter));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstFilter)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstFilter)));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteFilter() throws Exception {
        String id = firstFilter.getId();

        mockMvc.perform(delete(mapping + "/" + id))
                .andExpect(status().isNoContent());
    }
}