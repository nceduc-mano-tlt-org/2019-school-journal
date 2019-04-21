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
import ru.nceduc.journal.dto.AttendanceFilterDTO;
import ru.nceduc.journal.entity.AttendanceFilter;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.repository.AttendanceFilterRepository;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.service.AttendanceFilterService;

import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class AttendanceGroupServiceImplTest {

    @Autowired
    private AttendanceFilterService attendanceFilterService;

    @Autowired
    private AttendanceFilterRepository attendanceFilterRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ModelMapper modelMapper;

    private AttendanceFilter attendanceGroupOne;
    private AttendanceFilter attendanceGroupSecond;
    private AttendanceFilterDTO attendanceGroupDTO;
    private Group group;

    @Before
    public void before() {
        group = new Group("test","test");
        attendanceGroupOne = new AttendanceFilter();
        attendanceGroupOne.setMonth(1);
        attendanceGroupOne.setYear(2019);
        attendanceGroupSecond = new AttendanceFilter();
        attendanceGroupSecond.setMonth(2);
        attendanceGroupSecond.setYear(2019);
        attendanceGroupDTO = modelMapper.map(attendanceGroupOne,AttendanceFilterDTO.class);
    }

    @After
    public void after() {
        attendanceFilterRepository.deleteAll();
    }

    @Test
    public void getAllByGroupId() {
        Group actual = groupRepository.save(group);
        attendanceGroupOne.setGroup(actual);
        attendanceGroupSecond.setGroup(actual);
        attendanceFilterRepository.save(attendanceGroupOne);
        attendanceFilterRepository.save(attendanceGroupSecond);
        Collection<AttendanceFilterDTO> actualCollection = attendanceFilterService.getAllByGroupId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceGroupOne,AttendanceFilterDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceGroupSecond,AttendanceFilterDTO.class)));
    }

    @Test
    public void get() {
        AttendanceFilter actual = attendanceFilterRepository.save(attendanceGroupOne);
        Optional<AttendanceFilterDTO> actualOptional = attendanceFilterService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(attendanceGroupOne.getMonth(),actual.getMonth());
        Assert.assertEquals(attendanceGroupOne.getYear(), actual.getYear());
    }

    @Test
    public void getAll() {
        AttendanceFilter actualOne = attendanceFilterRepository.save(attendanceGroupOne);
        AttendanceFilter actualSecond = attendanceFilterRepository.save(attendanceGroupSecond);
        Collection<AttendanceFilterDTO> actualCollection = attendanceFilterService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,AttendanceFilterDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,AttendanceFilterDTO.class)));
    }

    @Test
    public void create() {
        Optional<AttendanceFilterDTO> actualOptional = attendanceFilterService.create(attendanceGroupDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(attendanceGroupDTO.getMonth(), actualOptional.get().getMonth());
        Assert.assertEquals(attendanceGroupDTO.getYear(), actualOptional.get().getYear());
    }

    @Test
    public void delete() {
        AttendanceFilter actual = attendanceFilterRepository.save(attendanceGroupOne);
        Optional<AttendanceFilter> actualOptional = attendanceFilterRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        attendanceFilterService.delete(actual.getId());
        actualOptional = attendanceFilterRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }
}