package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.repository.GroupRepository;
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
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<TeacherDTO> create(TeacherDTO entity) {
        Optional<TeacherDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            Teacher teacher = teacherRepository.save(modelMapper.map(optionalDTO.get(), Teacher.class));
            return Optional.of(modelMapper.map(teacher, TeacherDTO.class));
        }else
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (teacherRepository.findById(id).isPresent())
            teacherRepository.deleteById(id);
    }

    @Override
    public Optional<TeacherDTO> patch(TeacherDTO entity) {
        Optional<TeacherDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent() && teacherRepository.findById(optionalDTO.get().getId()).isPresent()) {
            TeacherDTO teacherDTO = modelMapper.map(this.get(optionalDTO.get().getId()), TeacherDTO.class);
            modelMapper.map(optionalDTO.get(), teacherDTO);
            return update(teacherDTO);
        }else
        return Optional.empty();
    }

    @Override
    public Optional<TeacherDTO> update(TeacherDTO entity) {
        Optional<TeacherDTO> optionalDTO = Optional.of(entity);
        String id = optionalDTO.get().getId();
        if (id != null && teacherRepository.findById(id).isPresent()) {
            Teacher teacher = modelMapper.map(optionalDTO.get(), Teacher.class);
            teacher.setCreatedDate(teacherRepository.findById(id).get().getCreatedDate());
            teacher.setModifiedDate(new Date());
            teacher = teacherRepository.save(teacher);
            return Optional.of(modelMapper.map(teacher, TeacherDTO.class));
        }else
        return Optional.empty();
    }

    @Override
    public Optional<TeacherDTO> get(String id) {
        if (teacherRepository.findById(id).isPresent()) {
            return Optional.of(modelMapper.map(teacherRepository.findById(id), TeacherDTO.class));
        }else
        return Optional.empty();
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
        Group group = groupRepository.findById(groupId).get();
        List<TeacherDTO> teacherDTO = new ArrayList<>();
        teacherRepository.findAllByGroup(group, Sort.by("createdDate").ascending()).forEach(teacher -> {
            teacherDTO.add(modelMapper.map(teacher, TeacherDTO.class));
        });
        return teacherDTO;
    }

}
