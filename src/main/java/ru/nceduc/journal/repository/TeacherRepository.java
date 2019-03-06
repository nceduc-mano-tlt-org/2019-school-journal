package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    List<Teacher> findAllByGroup(Group group, Sort sort);
}
