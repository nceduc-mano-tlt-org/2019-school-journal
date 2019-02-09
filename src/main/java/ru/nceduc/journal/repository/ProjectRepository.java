package ru.nceduc.journal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nceduc.journal.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, String> {
}
