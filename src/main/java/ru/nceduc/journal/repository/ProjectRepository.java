package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
