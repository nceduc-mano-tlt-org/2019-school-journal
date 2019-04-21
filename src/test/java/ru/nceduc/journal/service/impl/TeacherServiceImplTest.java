package ru.nceduc.journal.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.entity.Wallet;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.repository.TeacherRepository;
import ru.nceduc.journal.service.TeacherService;
import ru.nceduc.journal.service.WalletService;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class TeacherServiceImplTest {
    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ModelMapper modelMapper;

    private Teacher expectedTeacherOne;
    private Teacher expectedTeacherSecond;
    private TeacherDTO expectedTeacherDTO;
    private Group group;

    @Before
    public void before() {
        expectedTeacherOne = new Teacher();
        expectedTeacherOne.setFirstName("test");
        expectedTeacherOne.setLastName("test");
        expectedTeacherSecond = new Teacher();
        expectedTeacherSecond.setFirstName("test2");
        expectedTeacherSecond.setLastName("test2");
        expectedTeacherDTO = modelMapper.map(expectedTeacherOne, TeacherDTO.class);
        group = new Group("testGroup","testGroup");
    }

    @After
    public void after() {
        teacherRepository.deleteAllInBatch();
        expectedTeacherOne = null;
        expectedTeacherSecond = null;
        expectedTeacherDTO = null;
        group = null;
    }

    @Test
    public void create() {
        Optional<TeacherDTO> actualOptional = teacherService.create(expectedTeacherDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedTeacherDTO.getFirstName(), actualOptional.get().getFirstName());
        Assert.assertEquals(expectedTeacherDTO.getLastName(), actualOptional.get().getLastName());
    }

    @Test
    public void delete() {
        Teacher actual = teacherRepository.save(expectedTeacherOne);
        Optional<Teacher> actualOptional = teacherRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        teacherService.delete(actual.getId());
        actualOptional = teacherRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }

    @Test
    public void patch() {
        Teacher expected = teacherRepository.save(expectedTeacherOne);
        expected.setFirstName("patchTest");
        expected.setLastName("patchTest");
        teacherService.patch(modelMapper.map(expected, TeacherDTO.class));
        Optional<Teacher> actualOptional = teacherRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getFirstName(), actualOptional.get().getLastName());
        Assert.assertEquals(expected.getLastName(), actualOptional.get().getFirstName());
    }

    @Test
    public void update() {
        Teacher expected = teacherRepository.save(expectedTeacherOne);
        expected.setFirstName("patchTest");
        expected.setLastName("patchTest");
        teacherService.update(modelMapper.map(expected, TeacherDTO.class));
        Optional<Teacher> actualOptional = teacherRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getFirstName(), actualOptional.get().getLastName());
        Assert.assertEquals(expected.getLastName(), actualOptional.get().getFirstName());
    }

    @Test
    public void get() {
        Teacher actual = teacherRepository.save(expectedTeacherOne);
        Optional<TeacherDTO> actualOptional = teacherService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedTeacherOne.getFirstName(), actualOptional.get().getFirstName());
        Assert.assertEquals(expectedTeacherOne.getLastName(), actualOptional.get().getLastName());
    }

    @Test
    public void getAll() {
        Teacher actualOne = teacherRepository.save(expectedTeacherOne);
        Teacher actualSecond = teacherRepository.save(expectedTeacherSecond);
        Collection<TeacherDTO> actualCollection = teacherService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,TeacherDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,TeacherDTO.class)));
    }

    @Test
    public void getAllByGroupId() {
        Group actual = groupRepository.save(group);
        expectedTeacherOne.setGroup(actual);
        expectedTeacherSecond.setGroup(actual);
        teacherRepository.save(expectedTeacherOne);
        teacherRepository.save(expectedTeacherSecond);
        Collection<TeacherDTO> actualCollection = teacherService.getAllByGroupId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedTeacherOne,TeacherDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedTeacherSecond,TeacherDTO.class)));
    }
}