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
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.SectionRepository;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.SectionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class SectionServiceImplTest {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Section expectedSectionOne;
    private Section expectedSectionSecond;
    private SectionDTO expectedSectionDTO;
    private Project project;

    @Before
    public void before() {
        expectedSectionOne = new Section("test","test");
        expectedSectionSecond = new Section("test2","test2");
        expectedSectionDTO = modelMapper.map(expectedSectionOne, SectionDTO.class);
        project = new Project();
        project.setName("projectTest");
        project.setDescription("projectTest");
        List <Section> Sections = new ArrayList<>();
        Sections.add(expectedSectionOne);
        Sections.add(expectedSectionSecond);
        project.setSections(Sections);
    }

    @After
    public void after() {
        sectionRepository.deleteAllInBatch();
        expectedSectionOne = null;
        expectedSectionSecond = null;
        expectedSectionDTO = null;
        project = null;
    }

    @Test
    public void get() {
        Section actual = sectionRepository.save(expectedSectionOne);
        Optional<SectionDTO> actualOptional = sectionService.get(actual.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedSectionOne.getName(), actualOptional.get().getName());
        Assert.assertEquals(expectedSectionOne.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void getAll() {
        Section actualOne = sectionRepository.save(expectedSectionOne);
        Section actualSecond = sectionRepository.save(expectedSectionSecond);
        Collection<SectionDTO> actualCollection =sectionService.getAll();

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualOne,SectionDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(actualSecond,SectionDTO.class)));
    }

    @Test
    public void create() {
        Optional<SectionDTO> actualOptional = sectionService.create(expectedSectionDTO);
        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expectedSectionDTO.getName(), actualOptional.get().getName());
        Assert.assertEquals(expectedSectionDTO.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void patch() {
        Section expected = sectionRepository.save(expectedSectionOne);
        expected.setName("patchTest");
        expected.setDescription("patchTest");
        sectionService.patch(modelMapper.map(expected,SectionDTO.class));
        Optional<Section> actualOptional = sectionRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getName(), actualOptional.get().getName());
        Assert.assertEquals(expected.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void update() {
        Section expected = sectionRepository.save(expectedSectionOne);
        expected.setName("patchTest");
        expected.setDescription("patchTest");
        sectionService.update(modelMapper.map(expected,SectionDTO.class));
        Optional<Section> actualOptional = sectionRepository.findById(expected.getId());

        Assert.assertTrue(actualOptional.isPresent());
        Assert.assertEquals(expected.getName(), actualOptional.get().getName());
        Assert.assertEquals(expected.getDescription(), actualOptional.get().getDescription());
    }

    @Test
    public void delete() {
        Section actual = sectionRepository.save(expectedSectionOne);
        Optional<Section> actualOptional = sectionRepository.findById(actual.getId());
        Assert.assertTrue(actualOptional.isPresent());
        sectionService.delete(actual.getId());
        actualOptional = sectionRepository.findById(actual.getId());
        Assert.assertFalse(actualOptional.isPresent());
    }

    @Test
    public void getAllByProjectId() {
        Project actual = projectRepository.save(project);
        expectedSectionOne.setProject(actual);
        expectedSectionSecond.setProject(actual);
        sectionRepository.save(expectedSectionOne);
        sectionRepository.save(expectedSectionSecond);
        Collection<SectionDTO> actualCollection = sectionService.getAllByProjectId(actual.getId());

        Assert.assertFalse(actualCollection.isEmpty());
        Assert.assertEquals(2, actualCollection.size());
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedSectionOne,SectionDTO.class)));
        Assert.assertTrue(actualCollection.contains(modelMapper.map(expectedSectionSecond,SectionDTO.class)));
    }
}