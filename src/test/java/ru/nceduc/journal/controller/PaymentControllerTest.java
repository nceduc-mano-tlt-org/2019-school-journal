package ru.nceduc.journal.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.nceduc.journal.controller.rest.PaymentController;
import ru.nceduc.journal.service.PaymentService;
import ru.nceduc.journal.service.UserService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
@WithMockUser(authorities = "USER")
public class PaymentControllerTest {
    private final String mapping = "/api/v1/payment/";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

    }

    @Test
    public void getAll() {
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllByAdmin() {
    }

    @Test
    public void getAllByStudent() {
    }

    @Test
    public void deposit() {
    }

    @Test
    public void withdraw() {
    }

    @Test
    public void transfer() {
    }

    @Test
    public void cancel() {
    }
}