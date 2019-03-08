package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repositoryUser;
    private final ProjectRepository repositoryProject;
    private final ModelMapper modelMapper;

    @Override
    public UserEntity getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        UserEntity user = repositoryUser.findByUsername(username);
        return user;
    }

    @Override
    public UserDTO create(UserDTO entity) {
        if (entity != null && !repositoryUser.existsByUsername(entity.getUsername())) {
            Project project = new Project();
            Project projectInDB = repositoryProject.save(project);
            UserEntity userEntity = modelMapper.map(entity, UserEntity.class);
            userEntity.setProject(projectInDB);
            userEntity.setActive(true);
            userEntity.setRoles(Collections.singleton(Role.USER));
            repositoryUser.save(userEntity);
            return modelMapper.map(userEntity, UserDTO.class);
        } else
            return null;
    }

    @Override
    public void delete(String id) {
        if (id != null && repositoryUser.existsById(id))
        repositoryUser.deleteById(id);
    }

    @Override
    public UserDTO patch(UserDTO entity) {
        String id = entity.getId();
        UserDTO userDTO = this.get(id);
        modelMapper.map(entity, userDTO);
        return update(userDTO);
    }

    @Override
    public UserDTO update(UserDTO entity) {
        String id = entity.getId();
        if (id != null && repositoryUser.existsById(id)) {
            UserEntity userEntity = modelMapper.map(entity, UserEntity.class);
            userEntity.setCreatedDate(repositoryUser.findById(id).get().getCreatedDate());
            userEntity.setModifiedDate(new Date());
            repositoryUser.save(userEntity);
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public UserDTO get(String id) {
        UserEntity userEntity = repositoryUser.getOne(id);
        UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> all = new ArrayList<>();
        repositoryUser.findAll(Sort.by("createdDate").ascending()).forEach(user -> {
            all.add(modelMapper.map(user, UserDTO.class));
        });
        return all;
    }

    @Override
    public boolean getByName(String name){
        UserEntity userEntity = repositoryUser.findByUsername(name);
        if (userEntity == null){
            return false;
        }
        return true;
    }

    @Override
    public List<UserDTO> findAllByProject(String projectId) {
        Project project = repositoryProject.findById(projectId).get();
        List<UserDTO> userDTO = new ArrayList<>();
        repositoryUser.findAllByProject(project, Sort.by("createdDate").ascending()).forEach(user -> {
            userDTO.add(modelMapper.map(user, UserDTO.class));
        });
        return userDTO;
    }
}

