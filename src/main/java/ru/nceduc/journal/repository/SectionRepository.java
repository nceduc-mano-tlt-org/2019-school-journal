package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Section;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, String> {
    List<Section> findAll(Sort sort);
}