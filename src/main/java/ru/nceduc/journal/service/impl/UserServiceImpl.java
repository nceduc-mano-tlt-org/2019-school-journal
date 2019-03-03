package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repositoryUser;
    private final ProjectRepository repositoryProject;
    
    @Autowired
    private ModelMapper modelMapper;
    private UserEntity userEntity = null;

    public UserEntity getCurrentUsername() {
        User user = (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String name = user.getUsername();
        return repositoryUser.findByUsername(name);
    }

    @Override
    public UserDTO create(UserDTO entity) {
        if (entity != null) {
            Project project = new Project();
            Project projectInDB = repositoryProject.save(project);
            UserEntity userEntity = modelMapper.map(entity, UserEntity.class);
            userEntity.setProject(projectInDB);
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
        if (id != null && repositoryUser.existsById(id)) {
            UserDTO mainDTO = new UserDTO();
            modelMapper.map(entity, mainDTO);
            return getUserDTO(mainDTO);
        } else
            return null;
    }

    @Override
    public UserDTO update(UserDTO entity) {
        String id = entity.getId();
        if (id != null && repositoryUser.existsById(id)){
            return getUserDTO(entity);
        } else
            return null;
    }

    @Override
    public UserDTO get(String id) {
        userEntity = repositoryUser.getOne(id);
        UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        UserDTO projectDTO;
        List<UserDTO> all = new ArrayList<>();
        for(UserEntity user: repositoryUser.findAll()){
            all.add(modelMapper.map(user, UserDTO.class));
        }
        return all;
    }
    private UserDTO getUserDTO(UserDTO mainDTO) {
        UserEntity userEntity = modelMapper.map(mainDTO, UserEntity.class);
        userEntity.setModifiedDate(new Date());
        repositoryUser.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public boolean getByName(String name){
        userEntity = repositoryUser.findByUsername(name);
        if (userEntity == null){
            return false;
        }
        return true;
    }
}

