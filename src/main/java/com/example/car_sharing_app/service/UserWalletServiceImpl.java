package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.UserWallet;
import com.example.car_sharing_app.model.UserWalletHistory;
import com.example.car_sharing_app.repository.UserWalletHistoryRepository;
import com.example.car_sharing_app.repository.UserWalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    UserWalletRepository userWalletRepository;
    UserWalletHistoryRepository walletHistoryRepository;

    public UserWalletServiceImpl(UserWalletRepository userWalletRepository, UserWalletHistoryRepository walletHistoryRepository) {
        this.userWalletRepository = userWalletRepository;
        this.walletHistoryRepository = walletHistoryRepository;
    }

    @Override
    public UserWallet findUserWalletByUserId(Integer id) {
        return userWalletRepository.findUserWalletByUserId(id);
    }

    @Override
    public Double creditsWallet(Integer id, Double value) {
        UserWallet userWallet = userWalletRepository.findUserWalletByUserId(id);
        double newUserWalletValue = userWallet.getValue() + value;
        userWallet.setValue(newUserWalletValue);

        List<UserWalletHistory> walletHistories = userWallet.getWalletHistories();
        UserWalletHistory transaction = this.addTransaction(value);
        walletHistories.add(transaction);

        userWalletRepository.save(userWallet);
        return newUserWalletValue;
    }

    @Override
    public Double debitsWallet(Integer id, Double money) {
        UserWallet userWallet = userWalletRepository.findUserWalletByUserId(id);
        if (userWallet.getValue() >= money) {
            double newUserWalletValue = userWallet.getValue() - money;
            userWallet.setValue(newUserWalletValue);
            userWalletRepository.save(userWallet);
            return newUserWalletValue;
        } else {
            throw new IllegalStateException(
                    "Not enough money"
            );
        }
    }

    private UserWalletHistory addTransaction(Double value) {
        UserWalletHistory newTransaction = new UserWalletHistory();
        newTransaction.setTransaction_date(LocalDate.now());
        newTransaction.setType("Wpłata");
        newTransaction.setValue(value);
        return walletHistoryRepository.save(newTransaction);
    }

}
