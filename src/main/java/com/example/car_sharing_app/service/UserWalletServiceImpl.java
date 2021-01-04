package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserWallet;
import com.example.car_sharing_app.repository.UserRepository;
import com.example.car_sharing_app.repository.UserWalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserWalletServiceImpl implements UserWalletService{

    UserWalletRepository userWalletRepository;

    public UserWalletServiceImpl(UserWalletRepository userWalletRepository, UserRepository userRepository) {
        this.userWalletRepository = userWalletRepository;
    }

    @Override
    public UserWallet findUserWalletByUserId(User user) {
        return userWalletRepository.findUserWalletByUserId(user);
    }

    @Override
    public Double creditsWallet(User user, Double money) {
        UserWallet userWallet = userWalletRepository.findUserWalletByUserId(user);
        double newUserWalletValue = userWallet.getValue() + money;
        userWallet.setValue(newUserWalletValue);
        userWalletRepository.save(userWallet);
        return newUserWalletValue;
    }

    @Override
    public Double debitsWallet(User user, Double money) {
        UserWallet userWallet = userWalletRepository.findUserWalletByUserId(user);
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


}
