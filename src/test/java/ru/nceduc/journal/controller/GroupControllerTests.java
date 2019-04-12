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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        secondGroup = new GroupDTO(UUID.randomUUID().toString(), "Second group", "Desc for second group", null, 2000, sectionId);
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
    public void getAllGroups() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllGroupsByAdmin() throws Exception {
        Mockito.when(groupService.getAll()).thenReturn(allGroups);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(allGroups.toArray())));
    }

    @Test
    public void getGroupsBySectionId() throws Exception {
        String id = sectionDTO.getId();
        Mockito.when(groupService.getAllBySectionId(id)).thenReturn(groupsBySection);

        mockMvc.perform(get(mapping + "/by-section/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(groupsBySection.toArray())));

        mockMvc.perform(get(mapping + "/by-section/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void createGroup() throws Exception {
        Mockito.when(groupService.create(firstGroup)).thenReturn(Optional.of(firstGroup));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstGroup)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstGroup)));

        mockMvc.perform(post(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateGroup() throws Exception {
        Mockito.when(groupService.update(firstGroup)).thenReturn(Optional.of(firstGroup));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstGroup)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstGroup)));

        mockMvc.perform(put(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchGroup() throws Exception {
        Mockito.when(groupService.patch(firstGroup)).thenReturn(Optional.of(firstGroup));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(firstGroup)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(firstGroup)));

        mockMvc.perform(patch(mapping + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteGroup() {
    }
}