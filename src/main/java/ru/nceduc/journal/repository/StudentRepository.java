package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}
