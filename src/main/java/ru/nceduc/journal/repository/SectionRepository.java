package ru.nceduc.journal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nceduc.journal.entity.Section;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section, String> {
    List<Section> findAll();
}
