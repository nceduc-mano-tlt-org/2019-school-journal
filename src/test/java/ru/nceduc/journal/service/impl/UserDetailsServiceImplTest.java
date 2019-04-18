package ru.nceduc.journal.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.UserRepository;

import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class UserDetailsServiceImplTest {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @Before
    public void before(){
        userEntity = new UserEntity("test","test");
        userEntity.setRoles(Collections.singleton(Role.USER));
        userRepository.save(userEntity);
    }

    @After
    public void after() {
        userRepository.deleteAll();
        userEntity = null;
    }

    @Test
    public void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());
        Assert.assertEquals(userEntity.getUsername(),userDetails.getUsername());
        Assert.assertEquals(userEntity.getPassword(),userEntity.getPassword());
    }
}