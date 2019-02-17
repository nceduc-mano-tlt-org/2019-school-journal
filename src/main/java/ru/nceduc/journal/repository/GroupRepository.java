package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, String> {
    List<Group> findAll(Sort sort);
    List<Group> findAllBySection(Section section, Sort sort);
    boolean existsBySection(Section section);
}

