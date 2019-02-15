package ru.nceduc.journal.conversion;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Teacher;

public class TeacherEntityToDTO implements Converter<Teacher, TeacherDTO> {
    @Override
    public TeacherDTO convert(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacherDTO, teacher);
        return teacherDTO;
    }
}
