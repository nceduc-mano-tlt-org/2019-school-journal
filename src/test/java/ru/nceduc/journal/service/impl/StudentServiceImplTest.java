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
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.entity.Wallet;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.StudentService;
import ru.nceduc.journal.service.WalletService;

import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class StudentServiceImplTest {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @MockBean
    WalletService walletService;

    @Autowired
    private ModelMapper modelMapper;

    private Student expectedStudentOne;
    private Student expectedStudentSecond;
    private StudentDTO expectedStudentDTO;
    private Group group;
    private Wallet wallet;

    @Before
    public void before() {
        wallet = new Wallet();
        wallet.setBalance(1000);
        expectedStudentOne = new Student();
        expectedStudentOne.setFirstName("test");
        expectedStudentOne.setLastName("test");
        expectedStudentSecond = new Student();
        expectedStudentSecond.setFirstName("test2");
        expectedStudentSecond.setLastName("test2");
        expectedStudentDTO = modelMapper.map(expectedStudentOne, StudentDTO.class);
        group = new Group("testGroup","testGroup");
    }

    @After
    public void after() {
        studentRepository.deleteAllInBatch();
        expectedStudentOne = null;
        expectedStudentSecond = null;
        expectedStudentDTO = null;
        group = null;
    }

    @Test
    public void create() {
        Mockito.when(walletService.addWallet(expectedStudentOne)).thenReturn(wallet);

        Optional<StudentDTO> actualOptional = studentService.create(expectedStudentDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedStudentDTO.getFirstName(), actualOptional.get().getFirstName());
        Assert.assertEquals(expectedStudentDTO.getLastName(), actualOptional.get().getLastName());
    }

    @Test
    public void delete() {
        Student actual = studentRepository.save(expectedStudentOne);
        Optional<Student> actualOptional = studentRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        studentService.delete(actual.getId());
        actualOptional = studentRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }

    @Test
    public void patch() {
        Student expected = studentRepository.save(expectedStudentOne);
        expected.setFirstName("patchTest");
        expected.setLastName("patchTest");
        studentService.patch(modelMapper.map(expected, StudentDTO.class));
        Optional<Student> actualOptional = studentRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getFirstName(), actualOptional.get().getLastName());
        Assert.assertEquals(expected.getLastName(), actualOptional.get().getFirstName());
    }

    @Test
    public void update() {
        Student expected = studentRepository.save(expectedStudentOne);
        expected.setFirstName("patchTest");
        expected.setLastName("patchTest");
        studentService.update(modelMapper.map(expected, StudentDTO.class));
        Optional<Student> actualOptional = studentRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getFirstName(), actualOptional.get().getLastName());
        Assert.assertEquals(expected.getLastName(), actualOptional.get().getFirstName());
    }

    @Test
    public void get() {
        Student actual = studentRepository.save(expectedStudentOne);
        Optional<StudentDTO> actualOptional = studentService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedStudentOne.getFirstName(), actualOptional.get().getFirstName());
        Assert.assertEquals(expectedStudentOne.getLastName(), actualOptional.get().getLastName());
    }

    @Test
    public void getAll() {
        Student actualOne = studentRepository.save(expectedStudentOne);
        Student actualSecond = studentRepository.save(expectedStudentSecond);
        Collection<StudentDTO> actualCollection = studentService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,StudentDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,StudentDTO.class)));
    }

    @Test
    public void getAllByGroupId() {
        Group actual = groupRepository.save(group);
        expectedStudentOne.setGroup(actual);
        expectedStudentSecond.setGroup(actual);
        studentRepository.save(expectedStudentOne);
        studentRepository.save(expectedStudentSecond);
        Collection<StudentDTO> actualCollection = studentService.getAllByGroupId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedStudentOne,StudentDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedStudentSecond,StudentDTO.class)));
    }
}