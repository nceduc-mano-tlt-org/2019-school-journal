package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.PaymentService;
import ru.nceduc.journal.service.StudentService;
import ru.nceduc.journal.service.WalletService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PaymentService paymentService;
    private final WalletService walletService;
    private final ModelMapper modelMapper;

    @Override
    public Optional<StudentDTO> create(StudentDTO entity) {
        Optional<StudentDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            Student student = studentRepository.save(modelMapper.map(optionalDTO.get(), Student.class));
            walletService.addWallet(student);
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
        Optional<StudentDTO> patchedOptional = Optional.ofNullable(entity);
        if (patchedOptional.isPresent()) {
            Optional<StudentDTO> currentOptional = get(entity.getId());

            if (currentOptional.isPresent()) {
                StudentDTO patchedDTO = patchedOptional.get();
                StudentDTO currentDTO = currentOptional.get();
                modelMapper.map(patchedDTO, currentDTO);
                return save(currentDTO);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<StudentDTO> update(StudentDTO entity) {
        Optional<StudentDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            return save(optionalDTO.get());
        }
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
        List<StudentDTO> studentsDTO = new ArrayList<>();
        studentRepository.findAllByGroupId(groupId, Sort.by("createdDate").ascending()).forEach(student -> {
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            studentDTO.setLastDate(paymentService.getLastDate(student.getId(), groupId));
            studentsDTO.add(studentDTO);
        });
        return studentsDTO;
    }

    private Optional<StudentDTO> save(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);

        return studentRepository.findById(student.getId()).map(entity -> {
            student.setModifiedDate(new Date());
            student.setCreatedDate(entity.getCreatedDate());
            student.setGroup(entity.getGroup());
            Student savedStudent = studentRepository.save(student);
            return modelMapper.map(savedStudent, StudentDTO.class);
        });
    }

}
