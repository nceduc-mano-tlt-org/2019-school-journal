package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.DepositDTO;
import ru.nceduc.journal.dto.PaymentDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<PaymentDTO> get(String id);

    List<PaymentDTO> getAll();

    List<PaymentDTO> getAllByStudentId(String studentId);

    Date getLastDate(String studentId, String groupId);

    boolean transfer(PaymentDTO paymentDTO);

    boolean deposit(DepositDTO depositDTO);

    boolean withdraw(DepositDTO depositDTO);

    boolean cancel(String id);
}
