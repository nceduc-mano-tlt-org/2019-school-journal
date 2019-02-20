package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.StudentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDTO create(StudentDTO studentDTO, String parentId) {
        if (studentDTO != null) {
            Student student = modelMapper.map(studentDTO, Student.class);
            studentRepository.save(student);
            return studentDTO;
        } else
            return null;
    }

    @Override
    public void delete(String id) {
        if (id != null && studentRepository.existsById(id))
            studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO patch(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        String id = studentDTO.getId();
        if (id != null && studentRepository.existsById(id)){
            Student student = modelMapper.map(studentDTO, Student.class);
            student.setModifiedDate(new Date());
            studentRepository.save(student);
            return studentDTO;
        } else
            return null;
    }

    @Override
    public StudentDTO get(String id) {
        Student student = studentRepository.getOne(id);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll(Sort.by("createdDate").ascending());
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student element : students){
            studentDTOS.add(modelMapper.map(element, StudentDTO.class));
        }
        return studentDTOS;
    }
}
