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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Override
    public TeacherDTO create(TeacherDTO teacherDTO, String parentId) {
        if (teacherDTO != null) {
            Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
            teacherRepository.save(teacher);
            return modelMapper.map(teacher, TeacherDTO.class);
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
            TeacherDTO mainDTO = new TeacherDTO();
            modelMapper.map(teacherDTO, mainDTO);
            return getTeacherDTO(mainDTO);
        } else
            return null;
    }

    private TeacherDTO getTeacherDTO(TeacherDTO mainDTO) {
        Teacher teacher = modelMapper.map(mainDTO, Teacher.class);
        teacher.setModifiedDate(new Date());
        teacherRepository.save(teacher);

        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        String id = teacherDTO.getId();
        if (id != null && teacherRepository.existsById(id)){
            return getTeacherDTO(teacherDTO);
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
        List<Teacher> teachers = teacherRepository.findAll(Sort.by("createdDate").ascending());
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher element : teachers){
            teacherDTOS.add(modelMapper.map(element, TeacherDTO.class));
        }
        return teacherDTOS;
    }
}
