package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserWallet;

public interface UserWalletService {

    UserWallet findUserWalletByUserId(User user);

    Double creditsWallet(User user, Double money);

    Double debitsWallet(User user, Double money);
}
