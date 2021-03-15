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

        addTransactionToWalletHistory(value, userWallet);

        userWalletRepository.save(userWallet);
        return newUserWalletValue;
    }



    @Override
    public Boolean debitsWallet(Integer id, Double totalPrice) {
        UserWallet userWallet = userWalletRepository.findUserWalletByUserId(id);
        if (userWallet.getValue() >= totalPrice) {
            double newUserWalletValue = userWallet.getValue() - totalPrice;
            userWallet.setValue(newUserWalletValue);

            addTransactionToWalletHistory(-totalPrice, userWallet);

            userWalletRepository.save(userWallet);
            return true;
        } else {
            throw new IllegalStateException(
                    "Not enough money"
            );
        }
    }

    private void addTransactionToWalletHistory(Double value, UserWallet userWallet) {
        List<UserWalletHistory> walletHistory = userWallet.getWalletHistories();
        UserWalletHistory transaction = this.addTransaction(value);
        walletHistory.add(transaction);
    }

    private UserWalletHistory addTransaction(Double value) {
        UserWalletHistory newTransaction = new UserWalletHistory();
        newTransaction.setTransaction_date(LocalDate.now());
        if (value > 0) {
            newTransaction.setType("Wpłata");
        } else  {
            newTransaction.setType("Opłata za wynajem");
        }
        newTransaction.setValue(value);
        return walletHistoryRepository.save(newTransaction);
    }

}
