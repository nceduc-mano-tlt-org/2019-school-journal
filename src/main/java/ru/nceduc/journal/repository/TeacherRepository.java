package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
