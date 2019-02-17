package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public boolean create(Student entity, String parentId) {
        studentRepository.save(entity);
        return true;
    }

    @Override
    public boolean delete(String id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(String id, Student entity) {
        entity.setId(id);
        studentRepository.save(entity);
        return true;
    }

    @Override
    public Student get(String id) {
        return studentRepository.getOne(id);
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(studentRepository.findAll());
    }
}
