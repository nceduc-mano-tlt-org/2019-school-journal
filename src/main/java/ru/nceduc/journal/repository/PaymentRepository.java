package ru.nceduc.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nceduc.journal.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findAllByStudentId(String studentId);
    List<Payment> findAllByStudentIdAndGroupId(String studentId, String groupId);
}
