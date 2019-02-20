package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.GenericService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements GenericService<UserDTO>{

    private final UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    private UserEntity userEntity = null;

    public UserEntity getCurrentUsername() {
        User user = (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String name = user.getUsername();
        return repository.findByUsername(name);
    }

    @Override
    public UserDTO create(UserDTO entity) {
        userEntity = modelMapper.map(entity,UserEntity.class);
        repository.save(userEntity);
        return entity;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public UserDTO patch(UserDTO entity) {
        userEntity.setId(entity.getId());
        userEntity = repository.getOne(entity.getId());
        userEntity = modelMapper.map(entity,UserEntity.class);
        repository.save(userEntity);
        return entity;
    }

    @Override
    public UserDTO update(UserDTO entity) {
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        userEntity = modelMapper.map(entity,UserEntity.class);
        repository.save(userEntity);
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return entity;
    }

    @Override
    public UserDTO get(String id) {
        userEntity = repository.getOne(id);
        UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        UserDTO projectDTO;
        List<UserDTO> all = new ArrayList<>();
        for(UserEntity user: repository.findAll()){
            projectDTO = modelMapper.map(user, UserDTO.class);
            all.add(projectDTO);
        }
        return all;
    }
    public boolean getByName(String name){
        userEntity = repository.findByUsername(name);
        if (userEntity == null){
            return false;
        }
        return true;
    }
}

