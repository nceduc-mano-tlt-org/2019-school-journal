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
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.entity.AttendanceGroup;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.repository.AttendanceGroupRepository;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.service.AttendanceGroupService;

import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class AttendanceGroupServiceImplTest {

    @Autowired
    private AttendanceGroupService attendanceGroupService;

    @Autowired
    private AttendanceGroupRepository attendanceGroupRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ModelMapper modelMapper;

    private AttendanceGroup attendanceGroupOne;
    private AttendanceGroup attendanceGroupSecond;
    private AttendanceGroupDTO attendanceGroupDTO;
    private Group group;

    @Before
    public void before() {
        group = new Group("test","test");
        attendanceGroupOne = new AttendanceGroup();
        attendanceGroupOne.setMonth(1);
        attendanceGroupOne.setYear(2019);
        attendanceGroupSecond = new AttendanceGroup();
        attendanceGroupSecond.setMonth(2);
        attendanceGroupSecond.setYear(2019);
        attendanceGroupDTO = modelMapper.map(attendanceGroupOne,AttendanceGroupDTO.class);
    }

    @After
    public void after() {
        attendanceGroupRepository.deleteAll();
    }

    @Test
    public void getAllByGroupId() {
        Group actual = groupRepository.save(group);
        attendanceGroupOne.setGroup(actual);
        attendanceGroupSecond.setGroup(actual);
        attendanceGroupRepository.save(attendanceGroupOne);
        attendanceGroupRepository.save(attendanceGroupSecond);
        Collection<AttendanceGroupDTO> actualCollection = attendanceGroupService.getAllByGroupId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceGroupOne,AttendanceGroupDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceGroupSecond,AttendanceGroupDTO.class)));
    }

    @Test
    public void get() {
        AttendanceGroup actual = attendanceGroupRepository.save(attendanceGroupOne);
        Optional<AttendanceGroupDTO> actualOptional = attendanceGroupService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(attendanceGroupOne.getMonth(),actual.getMonth());
        Assert.assertEquals(attendanceGroupOne.getYear(), actual.getYear());
    }

    @Test
    public void getAll() {
        AttendanceGroup actualOne = attendanceGroupRepository.save(attendanceGroupOne);
        AttendanceGroup actualSecond = attendanceGroupRepository.save(attendanceGroupSecond);
        Collection<AttendanceGroupDTO> actualCollection =attendanceGroupService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,AttendanceGroupDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,AttendanceGroupDTO.class)));
    }

    @Test
    public void create() {
        Optional<AttendanceGroupDTO> actualOptional = attendanceGroupService.create(attendanceGroupDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(attendanceGroupDTO.getMonth(), actualOptional.get().getMonth());
        Assert.assertEquals(attendanceGroupDTO.getYear(), actualOptional.get().getYear());
    }

    @Test
    public void delete() {
        AttendanceGroup actual = attendanceGroupRepository.save(attendanceGroupOne);
        Optional<AttendanceGroup> actualOptional = attendanceGroupRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        attendanceGroupService.delete(actual.getId());
        actualOptional = attendanceGroupRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }
}