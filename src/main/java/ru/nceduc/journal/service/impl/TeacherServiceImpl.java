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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    public TeacherDTO create(TeacherDTO teacherDTO) {
        if (teacherDTO != null) {
            Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
            teacherRepository.save(teacher);
            return teacherDTO;
        } else
            return null;
    }

    @Override
    public void delete(String id) {
        if (id != null && teacherRepository.existsById(id))
            teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDTO patch(TeacherDTO teacherDTO) {
        String id = teacherDTO.getId();
        if (id != null && teacherRepository.existsById(id)) {
            TeacherDTO mainDTO = this.get(id);
            modelMapper.map(teacherDTO, mainDTO);
            return update(mainDTO);
        } else
            return null;
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO){
        String id = teacherDTO.getId();
        if (id != null && teacherRepository.existsById(id)){
            Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
            teacher.setCreatedDate(teacherRepository.findById(id).get().getCreatedDate());
            teacher.setModifiedDate(new Date());
            teacherRepository.save(teacher);
            return teacherDTO;
        } else
            return null;
    }

    @Override
    public TeacherDTO get(String id) {
        Teacher teacher = teacherRepository.getOne(id);
        return modelMapper.map(teacher, TeacherDTO.class);
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
