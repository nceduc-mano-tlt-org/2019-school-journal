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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    private UserEntity userEntityOne;
    private UserEntity getUserEntitySecond;
    private UserDTO userDTO;
    private Project project;

    @Before
    public void before() {
        userEntityOne = new UserEntity("test","test");
        userEntityOne.setRoles(Collections.singleton(Role.USER));
        getUserEntitySecond = new UserEntity("test2","test2");
        getUserEntitySecond.setRoles(Collections.singleton(Role.USER));
        userDTO = modelMapper.map(userEntityOne,UserDTO.class);
        project = new Project();
    }

    @After
    public void after() {
        userRepository.deleteAll();
        userEntityOne = null;
        getUserEntitySecond = null;
        userDTO = null;
        project = null;
    }

    @WithMockUser(username="test", password = "test", roles="USER")
    @Test
    public void getCurrentUser() {
        userRepository.save(userEntityOne);
        UserEntity actual = userService.getCurrentUser();

        Assert.assertEquals(userEntityOne.getPassword(),actual.getPassword());
        Assert.assertEquals(userEntityOne.getUsername(),actual.getUsername());
        Assert.assertEquals(userEntityOne.getRoles(),actual.getRoles());
    }

    @Test
    public void create() {
        Optional<UserDTO> actual = userService.create(userDTO);

        Assert.assertTrue(actual.isPresent());
        Assert.assertNotNull(userRepository.findById(actual.get().getId()));
        Assert.assertEquals(actual.get().getUsername(),userDTO.getUsername());
    }

    @Test
    public void delete() {
        UserEntity actual = userRepository.save(userEntityOne);
        Optional<UserEntity> actualOptional = userRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        userService.delete(actual.getId());
        actualOptional = userRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }

    @Test
    public void patch() {
        UserEntity expected = userRepository.save(userEntityOne);
        userEntityOne.setUsername("testUsername");
        userEntityOne.setRoles(Collections.singleton(Role.ADMIN));
        userService.patch(modelMapper.map(expected, UserDTO.class));
        Optional<UserEntity> actualOptional = userRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getUsername(), actualOptional.get().getUsername());
        Assert.assertEquals(expected.getRoles(), Collections.singleton(Role.ADMIN));
    }

    @Test
    public void update() {
        UserEntity expected = userRepository.save(userEntityOne);
        userEntityOne.setUsername("testUsername");
        userEntityOne.setRoles(Collections.singleton(Role.ADMIN));
        userService.update(modelMapper.map(expected, UserDTO.class));
        Optional<UserEntity> actualOptional = userRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getUsername(), actualOptional.get().getUsername());
        Assert.assertEquals(expected.getRoles(), Collections.singleton(Role.ADMIN));
    }

    @Test
    public void get() {
        UserEntity actual = userRepository.save(userEntityOne);
        Optional<UserDTO> actualOptional = userService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(userEntityOne.getUsername(), "test");
    }

    @Test
    public void getAll() {
        UserEntity actualOne = userRepository.save(userEntityOne);
        UserEntity actualSecond = userRepository.save(getUserEntitySecond);
        Collection<UserDTO> actualCollection = userService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,UserDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,UserDTO.class)));
    }

    @Test
    public void findAllByProject() {
        Project actualProject = projectRepository.save(project);
        userEntityOne.setProject(actualProject);
        getUserEntitySecond.setProject(actualProject);

        userRepository.save(userEntityOne);
        userRepository.save(getUserEntitySecond);

        Collection<UserDTO> actualCollection = userService.findAllByProject(actualProject.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(userEntityOne,UserDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(getUserEntitySecond,UserDTO.class)));
    }
}