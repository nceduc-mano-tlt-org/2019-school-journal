package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.entity.Wallet;
import ru.nceduc.journal.repository.WalletRepository;
import ru.nceduc.journal.service.WalletService;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Optional<Wallet> getByStudentId(String studentId) {
        return walletRepository.findByStudentId(studentId);
    }

    @Override
    public boolean deposit(Wallet wallet, long amount) {
        if (amount > 0) {
            long currentBalance = wallet.getBalance();
            wallet.setBalance(currentBalance + amount);
            walletRepository.save(wallet);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(Wallet wallet, long amount) {
        long currentBalance = wallet.getBalance();
        long newBalance = currentBalance - amount;
        if (amount > 0 && newBalance >= 0) {
            wallet.setBalance(newBalance);
            walletRepository.save(wallet);
            return true;
        }
        return false;
    }

    @Override
    public Wallet addWallet(Student student) {
        Wallet wallet = new Wallet();
        wallet.setStudent(student);
        return walletRepository.save(wallet);
    }

}
