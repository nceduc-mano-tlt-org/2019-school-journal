package ru.nceduc.journal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.nceduc.journal.controller.rest.PaymentController;
import ru.nceduc.journal.dto.DepositDTO;
import ru.nceduc.journal.dto.PaymentDTO;
import ru.nceduc.journal.service.PaymentService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
@WithMockUser(authorities = "USER")
public class PaymentControllerTests {
    private final String mapping = "/api/v1/payment";

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private PaymentDTO firstPayment;
    private PaymentDTO secondPayment;
    private PaymentDTO thirdPayment;
    private PaymentDTO wrongPayment;
    private DepositDTO depositDTO;
    private DepositDTO wrongDeposit;
    private List<PaymentDTO> allPayments;
    private List<PaymentDTO> paymentsByStudent;

    @Before
    public void setUp() {
        String firstGroupId = UUID.randomUUID().toString();
        String secondGroupId = UUID.randomUUID().toString();
        String firstStudentId = UUID.randomUUID().toString();
        String secondStudentId = UUID.randomUUID().toString();

        firstPayment = new PaymentDTO(firstStudentId, UUID.randomUUID().toString(), firstGroupId, 100);
        secondPayment = new PaymentDTO(firstStudentId, UUID.randomUUID().toString(), firstGroupId, 200);
        thirdPayment = new PaymentDTO(secondStudentId, UUID.randomUUID().toString(), secondGroupId, 300);
        wrongPayment = new PaymentDTO("invalidId", "invalidId", "invalidId", 0);
        depositDTO = new DepositDTO(firstStudentId, 200);
        wrongDeposit = new DepositDTO(secondStudentId, 0);

        allPayments = new ArrayList<>();
        allPayments.add(firstPayment);
        allPayments.add(secondPayment);
        allPayments.add(thirdPayment);

        paymentsByStudent = new ArrayList<>();
        paymentsByStudent.add(firstPayment);
        paymentsByStudent.add(secondPayment);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void getAllByAdmin() throws Exception {
        Mockito.when(paymentService.getAll()).thenReturn(allPayments);

        mockMvc.perform(get(mapping + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(allPayments.toArray())));
    }

    @Test
    public void getAllByStudent() throws Exception {
        String id = firstPayment.getId();
        Mockito.when(paymentService.getAllByStudentId(id)).thenReturn(paymentsByStudent);

        mockMvc.perform(get(mapping + "/by-student/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(paymentsByStudent.toArray())));

        mockMvc.perform(get(mapping + "/by-student/" + "invalidId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void deposit() throws Exception {
        Mockito.when(paymentService.deposit(depositDTO)).thenReturn(true);

        mockMvc.perform(put(mapping + "/deposit/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(depositDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(put(mapping + "/deposit/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongDeposit)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void withdraw() throws Exception {
        Mockito.when(paymentService.withdraw(depositDTO)).thenReturn(Boolean.TRUE);

        mockMvc.perform(put(mapping + "/withdraw/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(depositDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(put(mapping + "/withdraw/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongDeposit)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transfer() throws Exception {
        Mockito.when(paymentService.transfer(firstPayment)).thenReturn(Boolean.TRUE);

        mockMvc.perform(post(mapping + "/transfer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstPayment)))
                .andExpect(status().isOk());

        mockMvc.perform(post(mapping + "/transfer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongPayment)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cancel() throws Exception {
        String id = firstPayment.getId();
        Mockito.when(paymentService.cancel(id)).thenReturn(Boolean.TRUE);

        mockMvc.perform(delete(mapping + "/cancel/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(delete(mapping + "/cancel/" + "invalidId"))
                .andExpect(status().isBadRequest());
    }
}