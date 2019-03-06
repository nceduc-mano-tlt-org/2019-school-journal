package ru.nceduc.journal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByProject(Project project, Sort sort);
    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);
}
