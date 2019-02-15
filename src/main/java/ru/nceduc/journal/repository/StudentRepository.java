package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nceduc.journal.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
