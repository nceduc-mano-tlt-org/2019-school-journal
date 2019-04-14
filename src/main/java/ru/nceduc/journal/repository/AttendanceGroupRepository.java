package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.AttendanceGroup;

import java.util.List;

public interface AttendanceGroupRepository extends JpaRepository<AttendanceGroup, String> {
    List<AttendanceGroup> findAllByGroupId(String groupId);

//    List<AttendanceGroup> findAllByGroup_IdAndStudent_IdAndDateVisitBetween(String groupId, String studentId, Date startDate, Date endDate);
//
//    boolean existsByGroup_IdAndStudent_IdAndDateVisit(String group_id, String Student_id, Date date);
//
//    void deleteByGroup_IdAndStudent_IdAndDateVisit(String group_id, String Student_id, Date date);
}
