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
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.entity.AttendanceStudent;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.repository.AttendanceStudentRepository;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class AttendanceStudentServiceImplTest {

    @Autowired
    private AttendanceStudentService attendanceStudentService;

    @Autowired
    private AttendanceStudentRepository attendanceStudentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ModelMapper modelMapper;

    private AttendanceStudent attendanceStudentOne;
    private AttendanceStudent attendanceStudentSecond;
    private AttendanceStudentDTO attendanceStudentDTO;
    private Student student;
    private Group group;
    private GroupDTO groupDTO;

    @Before
    public void before() {
        attendanceStudentOne = new AttendanceStudent();
        attendanceStudentOne.setDateVisit(LocalDate.of(1,1,31));
        attendanceStudentSecond = new AttendanceStudent();
        attendanceStudentSecond.setDateVisit(LocalDate.of(1,1,31));
        attendanceStudentDTO = modelMapper.map(attendanceStudentOne,AttendanceStudentDTO.class);
        group = new Group("test","test");
        groupDTO = modelMapper.map(group, GroupDTO.class);
        student = new Student();
        student.setFirstName("testFirstName");
        student.setLastName("testLastName");
    }

    @After
    public void after() {
        attendanceStudentRepository.deleteAll();
        groupRepository.deleteAll();
        studentRepository.deleteAll();
        AttendanceStudent attendanceStudentOne = null;
        AttendanceStudent attendanceStudentSecond = null;
        AttendanceStudentDTO attendanceStudentDTO = null;
        Student student = null;
        Group group = null;
        GroupDTO groupDTO = null;
    }

    @Test
    public void getAllByGroupDTOId() {
        Group actual = groupRepository.save(group);
        groupDTO.setId(actual.getId());
        attendanceStudentOne.setGroup(actual);
        attendanceStudentSecond.setGroup(actual);
        attendanceStudentRepository.save(attendanceStudentOne);
        attendanceStudentRepository.save(attendanceStudentSecond);
        Collection<AttendanceStudentDTO> actualCollection = attendanceStudentService.getAllByGroupId(groupDTO.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceStudentOne,AttendanceStudentDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceStudentSecond,AttendanceStudentDTO.class)));
    }

    @Test
    public void getAllByGroupId() {
        Group actual = groupRepository.save(group);
        attendanceStudentOne.setGroup(actual);
        attendanceStudentSecond.setGroup(actual);
        attendanceStudentRepository.save(attendanceStudentOne);
        attendanceStudentRepository.save(attendanceStudentSecond);
        Collection<AttendanceStudentDTO> actualCollection = attendanceStudentService.getAllByGroupId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceStudentOne,AttendanceStudentDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(attendanceStudentSecond,AttendanceStudentDTO.class)));
    }

    @Test
    public void getAll() {
        AttendanceStudent actualOne = attendanceStudentRepository.save(attendanceStudentOne);
        AttendanceStudent actualSecond = attendanceStudentRepository.save(attendanceStudentSecond);
        Collection<AttendanceStudentDTO> actualCollection = attendanceStudentService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,AttendanceStudentDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,AttendanceStudentDTO.class)));
    }

    @Test
    public void get() {
        Group actualGroup = groupRepository.save(group);
        Student actualStudent = studentRepository.save(student);
        attendanceStudentOne.setGroup(actualGroup);
        attendanceStudentOne.setStudent(actualStudent);
        AttendanceStudent actual = attendanceStudentRepository.save(attendanceStudentOne);
        Optional<AttendanceStudentDTO> actualOptional = attendanceStudentService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(attendanceStudentOne.getDateVisit(),actualOptional.get().getDateVisit());
        Assert.assertEquals(attendanceStudentOne.getGroup(),actualGroup);
        Assert.assertEquals(attendanceStudentOne.getStudent(),actualStudent);
    }

    @Test
    public void create() {
        Optional<AttendanceStudentDTO> actualOptional = attendanceStudentService.create(attendanceStudentDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(attendanceStudentDTO.getDateVisit(), actualOptional.get().getDateVisit());
    }

    @Test
    public void delete() {
        AttendanceStudent actual = attendanceStudentRepository.save(attendanceStudentOne);
        Optional<AttendanceStudent> actualOptional = attendanceStudentRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        attendanceStudentService.delete(actual.getId());
        actualOptional = attendanceStudentRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }
}