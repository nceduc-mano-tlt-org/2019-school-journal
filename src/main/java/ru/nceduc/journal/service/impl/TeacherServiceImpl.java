package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.repository.TeacherRepository;
import ru.nceduc.journal.service.TeacherService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<TeacherDTO> create(TeacherDTO entity) {
        Optional<TeacherDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            Teacher teacher = teacherRepository.save(modelMapper.map(optionalDTO.get(), Teacher.class));
            return Optional.of(modelMapper.map(teacher, TeacherDTO.class));
        } else
            return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (teacherRepository.findById(id).isPresent())
            teacherRepository.deleteById(id);
    }

    @Override
    public Optional<TeacherDTO> patch(TeacherDTO entity) {
        Optional<TeacherDTO> patchedOptional = Optional.ofNullable(entity);
        if (patchedOptional.isPresent()) {
            Optional<TeacherDTO> currentOptional = get(entity.getId());

            if (currentOptional.isPresent()) {
                TeacherDTO patchedDTO = patchedOptional.get();
                TeacherDTO currentDTO = currentOptional.get();
                modelMapper.map(patchedDTO, currentDTO);
                return save(currentDTO);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<TeacherDTO> update(TeacherDTO entity) {
        Optional<TeacherDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            return save(optionalDTO.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<TeacherDTO> get(String id) {
        return teacherRepository.findById(id).map(teacher -> modelMapper.map(teacher, TeacherDTO.class));
    }

    @Override
    public List<TeacherDTO> getAll() {
        List<TeacherDTO> teacherDTO = new ArrayList<>();
        teacherRepository.findAll(Sort.by("createdDate").ascending()).forEach(teacher -> {
            teacherDTO.add(modelMapper.map(teacher, TeacherDTO.class));
        });
        return teacherDTO;
    }

    @Override
    public List<TeacherDTO> getAllByGroupId(String groupId) {
        List<TeacherDTO> teacherDTO = new ArrayList<>();
        teacherRepository.findAllByGroupId(groupId, Sort.by("createdDate").ascending()).forEach(teacher -> {
            teacherDTO.add(modelMapper.map(teacher, TeacherDTO.class));
        });
        return teacherDTO;
    }

    private Optional<TeacherDTO> save(TeacherDTO teacherDTO) {
        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);

        return teacherRepository.findById(teacher.getId()).map(entity -> {
            teacher.setModifiedDate(new Date());
            teacher.setCreatedDate(entity.getCreatedDate());
            teacher.setGroup(entity.getGroup());
            Teacher savedTeacher = teacherRepository.save(teacher);
            return modelMapper.map(savedTeacher, TeacherDTO.class);
        });
    }
}
