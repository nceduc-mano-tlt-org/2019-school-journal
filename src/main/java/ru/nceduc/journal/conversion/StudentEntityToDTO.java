package ru.nceduc.journal.conversion;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Student;

public class StudentEntityToDTO implements Converter<Student, StudentDTO> {
    @Override
    public StudentDTO convert(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(studentDTO, student);
        return studentDTO;
    }
}
