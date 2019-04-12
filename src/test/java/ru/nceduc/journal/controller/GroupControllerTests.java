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
import ru.nceduc.journal.controller.rest.GroupController;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.GroupService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
@WithMockUser(authorities = "USER")
public class GroupControllerTests {
    private final String mapping = "/api/v1/group";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private GroupService groupService;

    @Autowired
    private MockMvc mockMvc;

    private SectionDTO sectionDTO;
    private GroupDTO firstGroup;
    private GroupDTO secondGroup;
    private GroupDTO thirdGroup;
    private List<GroupDTO> allGroups;
    private List<GroupDTO> groupsBySection;

    @Before
    public void setUp() {
        String sectionId = UUID.randomUUID().toString();
        
        sectionDTO = new SectionDTO(sectionId, "English", "En description", UUID.randomUUID().toString());
        firstGroup = new GroupDTO(UUID.randomUUID().toString(), "First group", "Desc for first group", null, 1000, sectionId);
        secondGroup = new GroupDTO(UUID.randomUUID().toString(), "Second group", "Desc for second group",null, 2000, sectionId);
        thirdGroup = new GroupDTO(UUID.randomUUID().toString(), "Third group", "Desc for third group", null, 3000, UUID.randomUUID().toString());

        allGroups = new ArrayList<>();
        allGroups.add(firstGroup);
        allGroups.add(secondGroup);
        allGroups.add(thirdGroup);

        groupsBySection = new ArrayList<>();
        groupsBySection.add(firstGroup);
        groupsBySection.add(secondGroup);
    }

    @Test
    public void getGroup() throws Exception {
        String id = firstGroup.getId();
        Mockito.when(groupService.get(id)).thenReturn(Optional.of(firstGroup));

        mockMvc.perform(get(mapping + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstGroup)));

        mockMvc.perform(get(mapping + "/" + "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllGroups() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllGroupsByAdmin() {

    }

    @Test
    public void getGroupsBySectionId() {
    }

    @Test
    public void createGroup() {
    }

    @Test
    public void updateGroup() {
    }

    @Test
    public void patchGroup() {
    }

    @Test
    public void deleteGroup() {
    }
}