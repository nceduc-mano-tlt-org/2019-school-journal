package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.AttendanceFilter;

import java.util.List;

public interface AttendanceFilterRepository extends JpaRepository<AttendanceFilter, String> {
    List<AttendanceFilter> findAllByGroupId(String groupId);
}
