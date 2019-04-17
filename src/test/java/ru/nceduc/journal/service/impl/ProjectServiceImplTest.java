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
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.UserService;

import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class ProjectServiceImplTest {
    @Autowired
    private ProjectService projectService;

    @MockBean
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Project expectedProjectOne;
    private Project expectedProjectSecond;
    private ProjectDTO expectedProjectDTO;
    private UserEntity userEntity;

    @Before
    public void before() {
        expectedProjectOne = new Project();
        expectedProjectOne.setName("test");
        expectedProjectOne.setDescription("test");
        expectedProjectSecond = new Project();
        expectedProjectSecond.setName("test2");
        expectedProjectSecond.setDescription("test2");
        expectedProjectDTO = modelMapper.map(expectedProjectOne, ProjectDTO.class);
        userEntity = new UserEntity("user","test");
        userEntity.setProject(expectedProjectOne);
    }

    @After
    public void after() {
        projectRepository.deleteAllInBatch();
        expectedProjectOne = null;
        expectedProjectSecond = null;
        expectedProjectDTO = null;
    }

    @Test
    public void create() {
        Optional<ProjectDTO> actualOptional = projectService.create(expectedProjectDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedProjectDTO.getName(), actualOptional.get().getName());
        Assert.assertEquals(expectedProjectDTO.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void delete() {
        Project actual = projectRepository.save(expectedProjectOne);
        Optional<Project> actualOptional = projectRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        projectService.delete(actual.getId());
        actualOptional = projectRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }

    @Test
    public void patch() {
        Project expected = projectRepository.save(expectedProjectOne);
        expected.setName("patchTest");
        expected.setDescription("patchTest");
        projectService.patch(modelMapper.map(expected, ProjectDTO.class));
        Optional<Project> actualOptional = projectRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getName(), actualOptional.get().getName());
        Assert.assertEquals(expected.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void update() {
        Project expected = projectRepository.save(expectedProjectOne);
        expected.setName("patchTest");
        expected.setDescription("patchTest");
        projectService.update(modelMapper.map(expected, ProjectDTO.class));
        Optional<Project> actualOptional = projectRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getName(), actualOptional.get().getName());
        Assert.assertEquals(expected.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void get() {
        Project actual = projectRepository.save(expectedProjectOne);
        Optional<ProjectDTO> actualOptional = projectService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedProjectOne.getName(), actualOptional.get().getName());
        Assert.assertEquals(expectedProjectOne.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void getAll() {
        Project actualOne = projectRepository.save(expectedProjectOne);
        Project actualSecond = projectRepository.save(expectedProjectSecond);
        Collection<ProjectDTO> actualCollection = projectService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,ProjectDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,ProjectDTO.class)));
    }

    @Test
    public void getCurrentProject() {
        Mockito.when(userService.getCurrentUser()).thenReturn(userEntity);
        ProjectDTO actual = projectService.getCurrentProject();

        Assert.assertEquals(actual.getName(), expectedProjectOne.getName());
        Assert.assertEquals(actual.getDescription(), expectedProjectOne.getDescription());
    }

    @Test
    public void getAllByCurrentUser() {
        Mockito.when(userService.getCurrentUser()).thenReturn(userEntity);

        Project actualOne = projectRepository.save(expectedProjectOne);
        userEntity.setProject(actualOne);
        Collection<ProjectDTO> actualCollection = projectService.getAllByCurrentUser();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(1, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,ProjectDTO.class)));
    }
}
