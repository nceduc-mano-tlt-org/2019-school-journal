package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.repository.TeacherRepository;
import ru.nceduc.journal.service.TeacherService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public boolean create(Teacher entity, String parentId) {
        teacherRepository.save(entity);
        return true;
    }

    @Override
    public boolean delete(String id) {
        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(String id, Teacher entity) {
        entity.setId(id);
        teacherRepository.save(entity);
        return true;
    }

    @Override
    public Teacher get(String id) {
        return teacherRepository.getOne(id);
    }

    @Override
    public List<Teacher> getAll() {
        return new ArrayList<>(teacherRepository.findAll());
    }
}
