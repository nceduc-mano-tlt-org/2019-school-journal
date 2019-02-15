package ru.nceduc.journal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.repository.TeacherRepository;
import ru.nceduc.journal.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ConversionService conversionService;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ConversionService conversionService) {
        this.teacherRepository = teacherRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void create(Teacher entity) {
        teacherRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void patch(String id, Map<String, Teacher> patchValues) {
        Teacher teacher = teacherRepository.getOne(id);
        TeacherDTO dto = new TeacherDTO();
        //TODO: Доделать!!!
    }

    @Override
    public void update(String id, Teacher entity) {
        entity.setId(id);
        teacherRepository.save(entity);
    }

    @Override
    public TeacherDTO get(String id) {
        Teacher teacher = teacherRepository.getOne(id);
        return conversionService.convert(teacher, TeacherDTO.class);
    }

    @Override
    public List<TeacherDTO> getAll() {
        List<Teacher> teachers = new ArrayList<>(teacherRepository.findAll());
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher t : teachers){
            teacherDTOS.add(conversionService.convert(t,TeacherDTO.class));
        }
        return teacherDTOS;
    }
}
