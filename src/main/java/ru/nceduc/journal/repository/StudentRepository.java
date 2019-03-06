package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllByGroup(Group group, Sort sort);
}
