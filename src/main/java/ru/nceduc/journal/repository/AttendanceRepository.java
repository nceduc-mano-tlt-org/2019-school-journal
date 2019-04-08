package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    List<Attendance> findAllByGroup_IdAndStudent_IdAndDateVisitBetween(String groupId, String studentId, Date startDate, Date endDate);

    boolean existsByGroup_IdAndStudent_IdAndDateVisit(String group_id, String Student_id, Date date);

    void deleteByGroup_IdAndStudent_IdAndDateVisit(String group_id, String Student_id, Date date);
}
