package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.AttendanceStudent;

import java.util.List;

public interface AttendanceStudentRepository extends JpaRepository<AttendanceStudent, String> {
    List<AttendanceStudent> findAllByGroupId(String groupId);
}
