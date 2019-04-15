package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.AttendanceGroup;

import java.util.List;

public interface AttendanceGroupRepository extends JpaRepository<AttendanceGroup, String> {
    List<AttendanceGroup> findAllByGroupId(String groupId);
}
