package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.User;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.GenericService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements GenericService<User>{

    private final UserRepository repository;

    @Override
    public void create(User entity) {
        repository.save(entity);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void patch(String id, User entity) {

    }

    @Override
    public void update(String id, User entity) {

    }

    @Override
    public User get(String id) {
        return repository.findById(id).get();
    }

    public User getByName(String username){
        return repository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        List<User> all = new ArrayList<>();
        repository.findAll().forEach(all::add);
        return all;
    }

    public User getCurrentUsername() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String name = user.getUsername();
        return repository.findByUsername(name);
    }

}
