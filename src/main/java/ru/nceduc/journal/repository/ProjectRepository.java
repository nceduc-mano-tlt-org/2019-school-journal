package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.UserEntity;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findAllByUser(UserEntity user, Sort sort);
}
