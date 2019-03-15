package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repositoryUser;
    private final ProjectRepository repositoryProject;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return repositoryUser.findByUsername(username);
    }

    @Override
    public Optional<UserDTO> create(UserDTO entity) {
        Optional<UserDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent() && !repositoryUser.existsByUsername(entity.getUsername())) {
            Project project = new Project();
            Project projectInDB = repositoryProject.save(project);
            UserEntity userEntity = modelMapper.map(entity, UserEntity.class);
            userEntity.setProject(projectInDB);
            userEntity.setActive(true);

            userEntity.setPassword(passwordEncoder.encode(entity.getPassword()));

            userEntity.setRoles(Collections.singleton(Role.USER));
            UserEntity user = repositoryUser.save(userEntity);
            return Optional.of(modelMapper.map(user, UserDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (id != null && repositoryUser.existsById(id))
        repositoryUser.deleteById(id);
    }

    @Override
    public Optional<UserDTO> patch(UserDTO entity) {
        Optional<UserDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent() && repositoryUser.findById(optionalDTO.get().getId()).isPresent()) {
            UserDTO userDTO = modelMapper.map(this.get(optionalDTO.get().getId()), UserDTO.class);
            modelMapper.map(optionalDTO.get(), userDTO);
            return update(userDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> update(UserDTO entity) {
        Optional<UserDTO> optionalDTO = Optional.of(entity);
        String id = optionalDTO.get().getId();
        if (id != null && repositoryUser.findById(id).isPresent()) {
            UserEntity userEntity = modelMapper.map(optionalDTO.get(), UserEntity.class);
            userEntity.setCreatedDate(repositoryUser.findById(id).get().getCreatedDate());
            userEntity.setModifiedDate(new Date());
            userEntity = repositoryUser.save(userEntity);
            return Optional.of(modelMapper.map(userEntity, UserDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> get(String id) {
        if (repositoryUser.findById(id).isPresent()) {
            return Optional.of(modelMapper.map(repositoryUser.findById(id), UserDTO.class));
        }
        return Optional.empty();
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
    public List<UserDTO> findAllByProject(String projectId) {
        List<UserDTO> userDTO = new ArrayList<>();
        repositoryUser.findAllByProjectId(projectId, Sort.by("createdDate").ascending()).forEach(user -> {
            userDTO.add(modelMapper.map(user, UserDTO.class));
        });
        return userDTO;
    }
}


