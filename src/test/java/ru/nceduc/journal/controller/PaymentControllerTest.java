package ru.nceduc.journal.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

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

    private PaymentDTO firstPayment;
    private PaymentDTO secondPayment;
    private PaymentDTO thirdPayment;
    private DepositDTO depositDTO;
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
        depositDTO = new DepositDTO(UUID.randomUUID().toString(), firstStudentId, 200);

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
                .andExpect(content().json(toJson(allPayments.toArray())));
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