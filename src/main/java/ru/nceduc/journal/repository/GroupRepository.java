package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, String> {
    List<Group> findAll(Sort sort);
    List<Group> findAllBySectionId(String sectionId, Sort sort);
}