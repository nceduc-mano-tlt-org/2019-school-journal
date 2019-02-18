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
        repository.deleteById(id);
    }

    @Override
    public void patch(String id, User entity) {
        User actual = repository.findById(id).get();
        repository.deleteById(id);
        if(entity.getPassword() != null){
            actual.setPassword(entity.getPassword());
        }
        if(entity.getModifiedDate() != null){
            actual.setModifiedDate(entity.getModifiedDate());
        }
        if(entity.getProject() != null){
            actual.setProject(entity.getProject());
        }
        if(entity.getRoles() != null){
            actual.setRoles(entity.getRoles());
        }
        repository.save(actual);
    }

    @Override
    public void update(String id, User entity) {
        repository.save(entity);
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
