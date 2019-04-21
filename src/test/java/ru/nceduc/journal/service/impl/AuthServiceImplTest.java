package ru.nceduc.journal.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.UserRepository;
import ru.nceduc.journal.service.AuthService;

import java.security.Security;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
public class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Autowired
    UserRepository userRepository;

    @MockBean
    AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    private UserEntity userEntity;

    @Before
    public void before() {
        userEntity = new UserEntity("test","test");
        userRepository.save(userEntity);
    }

    @After
    public void after() {
        userRepository.deleteAll();
        userEntity = null;
    }

    @WithMockUser(username="test", password = "test", roles="USER")
    @Test
    public void authUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.assertNotNull(principal);
        UserDTO userDTO = modelMapper.map(((UserDetails)principal),UserDTO.class);
        authService.authUser(userDTO);
        Assert.assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }
}