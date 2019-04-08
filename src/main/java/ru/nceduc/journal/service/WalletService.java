package ru.nceduc.journal.service;

import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.entity.Wallet;

import java.util.Optional;

public interface WalletService {
    Optional<Wallet> getByStudentId(String studentId);

    Wallet addWallet(Student student);

    boolean deposit(Wallet wallet, long amount);

    boolean withdraw(Wallet wallet, long amount);


}
