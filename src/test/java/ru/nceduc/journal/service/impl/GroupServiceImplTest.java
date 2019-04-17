package ru.nceduc.journal.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.repository.SectionRepository;
import ru.nceduc.journal.service.GroupService;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class GroupServiceImplTest {
    @Autowired
    GroupService groupService;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Group expectedGroupOne;
    private Group expectedGroupSecond;
    private GroupDTO expectedGroupDTO;
    private Section section;

    @Before
    public void before() {
        expectedGroupOne = new Group("test","test");
        expectedGroupSecond = new Group("test2","test2");
        expectedGroupDTO = modelMapper.map(expectedGroupOne,GroupDTO.class);
        section = new Section();
    }

    @After
    public void after() {
        groupRepository.deleteAllInBatch();
        expectedGroupOne = null;
        expectedGroupSecond = null;
        expectedGroupDTO = null;
        section = null;
    }

    @Test
    public void get() {
        Group actual = groupRepository.save(expectedGroupOne);
        Optional<GroupDTO> actualOptional = groupService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedGroupOne.getName(), actualOptional.get().getName());
        Assert.assertEquals(expectedGroupOne.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void getAll() {
        Group actualOne = groupRepository.save(expectedGroupOne);
        Group actualSecond = groupRepository.save(expectedGroupSecond);
        Collection<GroupDTO> actualCollection =groupService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,GroupDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,GroupDTO.class)));
    }

    @Test
    public void create() {
        Optional<GroupDTO> actualOptional = groupService.create(expectedGroupDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedGroupDTO.getName(), actualOptional.get().getName());
        Assert.assertEquals(expectedGroupDTO.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void patch() {
        Group expected = groupRepository.save(expectedGroupOne);
        expected.setName("patchTest");
        expected.setDescription("patchTest");
        groupService.patch(modelMapper.map(expected, GroupDTO.class));
        Optional<Group> actualOptional = groupRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getName(), actualOptional.get().getName());
        Assert.assertEquals(expected.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void update() {
        Group expected = groupRepository.save(expectedGroupOne);
        expected.setName("patchTest");
        expected.setDescription("patchTest");
        groupService.update(modelMapper.map(expected, GroupDTO.class));
        Optional<Group> actualOptional = groupRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getName(), actualOptional.get().getName());
        Assert.assertEquals(expected.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void delete() {
        Group actual = groupRepository.save(expectedGroupOne);
        Optional<Group> actualOptional = groupRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        groupService.delete(actual.getId());
        actualOptional = groupRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }

    @Test
    public void getAllBySectionId() {
        Section actual = sectionRepository.save(section);
        expectedGroupOne.setSection(actual);
        expectedGroupSecond.setSection(actual);
        groupRepository.save(expectedGroupOne);
        groupRepository.save(expectedGroupSecond);
        Collection<GroupDTO> actualCollection = groupService.getAllBySectionId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedGroupOne,GroupDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedGroupSecond,GroupDTO.class)));
    }
}