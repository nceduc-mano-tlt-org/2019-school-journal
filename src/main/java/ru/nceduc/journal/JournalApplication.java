package ru.nceduc.journal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.impl.UserServiceImpl;

import java.util.Collections;

@SpringBootApplication
public class JournalApplication {

    @Autowired
    private static UserServiceImpl service;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
        UserEntity user = new UserEntity("1","1");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        //user.setId(UUID.randomUUID().toString());
        UserRepository rep = context.getBean(UserRepository.class);
        rep.save(user);
        //service.create(user);
        ModelMapper modelMapper = new ModelMapper();
        UserEntity UserTest = new UserEntity("testLogin","testPass");
        UserTest.setActive(true);
        UserTest.setRoles(Collections.singleton(Role.USER));
        UserTest.setId("idddd");
        UserDTO userDTO = null;
        userDTO = modelMapper.map(UserTest,UserDTO.class);

    }
}

