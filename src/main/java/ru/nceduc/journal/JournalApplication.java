package ru.nceduc.journal;

import org.hibernate.engine.HibernateIterator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.impl.ProjectServiceImpl;
import ru.nceduc.journal.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
public class JournalApplication {

    @Autowired
    private static UserServiceImpl service;
    @Autowired
    private static ProjectServiceImpl serviceProjet;
    @Autowired
    private static ModelMapper modelMapper;
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
        UserEntity user = new UserEntity("1","1");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        UserRepository rep = context.getBean(UserRepository.class);
        ProjectRepository repProject = context.getBean(ProjectRepository.class);

        Project project = new Project();
        /*Project pr = repProject.save(project);
        user.setProject(repProject.getOne(pr.getId()));*/
        Project pr = repProject.save(project);
        user.setProject(pr);
        rep.save(user);


    }
}

