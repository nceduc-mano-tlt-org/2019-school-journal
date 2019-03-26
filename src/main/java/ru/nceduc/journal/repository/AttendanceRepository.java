package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Attendance;
import ru.nceduc.journal.entity.Student;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    //List<Attendance> findAllByDateVisitBetween(Date startDate, Date endDate);
    List<Attendance> findAllByGroup_IdAndStudent_IdAndDateVisitBetween(String groupId, String studentId, Date startDate, Date endDate);
    boolean existsByGroup_IdAndStudent_Id(String group_id, String Student_id);
    void deleteByGroup_IdAndStudent_Id(String group_id, String Student_id);
}
