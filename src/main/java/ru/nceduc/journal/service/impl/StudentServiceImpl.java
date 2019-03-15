package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.StudentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<StudentDTO> create(StudentDTO entity) {
        Optional<StudentDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            Student student = studentRepository.save(modelMapper.map(optionalDTO.get(), Student.class));
            return Optional.of(modelMapper.map(student, StudentDTO.class));
        } else
            return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (studentRepository.findById(id).isPresent())
            studentRepository.deleteById(id);
    }

    @Override
    public Optional<StudentDTO> patch(StudentDTO entity) {
        Optional<StudentDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent() && studentRepository.findById(optionalDTO.get().getId()).isPresent()) {
            StudentDTO studentDTO = modelMapper.map(this.get(optionalDTO.get().getId()), StudentDTO.class);
            modelMapper.map(optionalDTO.get(), studentDTO);
            return update(studentDTO);
        } else
            return Optional.empty();
    }

    @Override
    public Optional<StudentDTO> update(StudentDTO entity) {
        Optional<StudentDTO> optionalDTO = Optional.of(entity);
        String id = optionalDTO.get().getId();
        if (id != null && studentRepository.findById(id).isPresent()) {
            Student student = modelMapper.map(optionalDTO.get(), Student.class);
            student.setCreatedDate(studentRepository.findById(id).get().getCreatedDate());
            student.setModifiedDate(new Date());
            student = studentRepository.save(student);
            return Optional.of(modelMapper.map(student, StudentDTO.class));
        } else
            return Optional.empty();
    }

    @Override
    public Optional<StudentDTO> get(String id) {
        return studentRepository.findById(id).map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTO = new ArrayList<>();
        studentRepository.findAll(Sort.by("createdDate").ascending()).forEach(student -> {
            studentDTO.add(modelMapper.map(student, StudentDTO.class));
        });
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllByGroupId(String groupId) {
        List<StudentDTO> studentDTO = new ArrayList<>();
        studentRepository.findAllByGroupId(groupId, Sort.by("createdDate").ascending()).forEach(student -> {
            studentDTO.add(modelMapper.map(student, StudentDTO.class));
        });
        return studentDTO;
    }
}
