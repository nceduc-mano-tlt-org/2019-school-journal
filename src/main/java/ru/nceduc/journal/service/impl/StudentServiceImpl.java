package ru.nceduc.journal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ConversionService conversionService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ConversionService conversionService) {
        this.studentRepository = studentRepository;
        this.conversionService = conversionService;
    }


    @Override
    public void create(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void patch(String id, Map<String, Student> patchValues) {
        Student student = studentRepository.getOne(id);
        StudentDTO dto = new StudentDTO();
        //TODO: Доделать!!!
    }

    @Override
    public void update(String id, Student entity) {
        entity.setId(id);
        studentRepository.save(entity);
    }

    @Override
    public StudentDTO get(String id) {
        Student Student = studentRepository.getOne(id);
        return conversionService.convert(Student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAll() {
        List<Student> students = new ArrayList<>(studentRepository.findAll());
        List<StudentDTO> StudentDTOS = new ArrayList<>();
        for (Student t : students){
            StudentDTOS.add(conversionService.convert(t,StudentDTO.class));
        }
        return StudentDTOS;
    }
}
