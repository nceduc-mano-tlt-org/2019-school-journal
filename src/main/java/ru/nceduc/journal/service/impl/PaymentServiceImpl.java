package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.DepositDTO;
import ru.nceduc.journal.dto.PaymentDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Payment;
import ru.nceduc.journal.entity.Wallet;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.repository.PaymentRepository;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.PaymentService;
import ru.nceduc.journal.service.WalletService;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final WalletService walletService;
    private final ModelMapper modelMapper;

    @Override
    public Optional<PaymentDTO> get(String id) {
        return paymentRepository.findById(id).map(payment -> modelMapper.map(payment, PaymentDTO.class));
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<PaymentDTO> paymentsDTO = new ArrayList<>();
        paymentRepository.findAll().forEach(payment -> {
            paymentsDTO.add(modelMapper.map(payment, PaymentDTO.class));
        });
        return paymentsDTO;
    }

    @Override
    public List<PaymentDTO> getAllByStudentId(String studentId) {
        List<PaymentDTO> paymentsDTO = new ArrayList<>();
        paymentRepository.findAllByStudentId(studentId).forEach(payment -> {
            paymentsDTO.add(modelMapper.map(payment, PaymentDTO.class));
        });
        return paymentsDTO;
    }

    @Override
    public Date getLastDate(String studentId, String groupId) {
        int months;
        long totalAmount = getTotalAmount(studentId, groupId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            Date startDate = group.getStartDate();
            long groupCost = group.getCost();
            if (groupCost > 0) {
                months = (int) (totalAmount / groupCost);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.add(Calendar.MONTH, months);

                return calendar.getTime();
            }
        }
        return null;
    }

    @Override
    public boolean transfer(PaymentDTO inputDTO) {
        Optional<PaymentDTO> paymentDTOOptional = Optional.ofNullable(inputDTO);
        if (paymentDTOOptional.isPresent()) {
            PaymentDTO paymentDTO = paymentDTOOptional.get();
            String groupId = paymentDTO.getGroupId();
            if (studentRepository.existsByGroupId(groupId)) {
                Optional<Wallet> walletOptional = walletService.getByStudentId(paymentDTO.getStudentId());
                if (walletOptional.isPresent()) {
                    Wallet wallet = walletOptional.get();
                    boolean isWithdraw = walletService.withdraw(wallet, paymentDTO.getAmount());
                    if (isWithdraw) {
                        Payment payment = modelMapper.map(paymentDTO, Payment.class);
                        paymentRepository.save(payment);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean deposit(DepositDTO inputDTO) {
        Optional<DepositDTO> depositDTOOptional = Optional.ofNullable(inputDTO);
        if (depositDTOOptional.isPresent()) {
            DepositDTO depositDTO = depositDTOOptional.get();
            Optional<Wallet> walletOptional = walletService.getByStudentId(depositDTO.getStudentId());
            if (walletOptional.isPresent()) {
                Wallet wallet = walletOptional.get();

                return walletService.deposit(wallet, depositDTO.getAmount());
            }
        }
        return false;
    }

    @Override
    public boolean withdraw(DepositDTO inputDTO) {
        Optional<DepositDTO> depositDTOOptional = Optional.ofNullable(inputDTO);
        if (depositDTOOptional.isPresent()) {
            DepositDTO depositDTO = depositDTOOptional.get();
            Optional<Wallet> walletOptional = walletService.getByStudentId(depositDTO.getStudentId());
            if (walletOptional.isPresent()) {
                Wallet wallet = walletOptional.get();

                return walletService.withdraw(wallet, depositDTO.getAmount());
            }
        }
        return false;
    }

    @Override
    public boolean cancel(String id) {
        Optional<PaymentDTO> paymentDTOOptional = get(id);
        if (paymentDTOOptional.isPresent()) {
            PaymentDTO paymentDTO = paymentDTOOptional.get();
            DepositDTO depositDTO = new DepositDTO(paymentDTO.getStudentId(), paymentDTO.getAmount());
            boolean isDeposit = deposit(depositDTO);
            if (isDeposit) {
                paymentRepository.deleteById(paymentDTO.getId());
                return true;
            }
        }
        return false;
    }

    private long getTotalAmount(String studentId, String groupId) {
        long result = 0;
        List<Payment> payments = paymentRepository.findAllByStudentIdAndGroupId(studentId, groupId);
        for (Payment payment : payments) {
            result += payment.getAmount();
        }
        return result;
    }

}
