package ru.nceduc.journal;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.User;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
public class JournalApplication {

    @Autowired
    private static UserServiceImpl service;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
        User user = new User("1","1");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        //user.setId(UUID.randomUUID().toString());
        UserRepository rep = context.getBean(UserRepository.class);
        rep.save(user);
        //service.create(user);
    }
}

