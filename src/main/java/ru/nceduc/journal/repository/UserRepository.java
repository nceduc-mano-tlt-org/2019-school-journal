package ru.nceduc.journal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nceduc.journal.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
}
