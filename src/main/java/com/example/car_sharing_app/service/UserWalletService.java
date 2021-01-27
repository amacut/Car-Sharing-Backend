package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserWallet;
import com.example.car_sharing_app.model.UserWalletHistory;

import java.util.Set;

public interface UserWalletService {

    UserWallet findUserWalletByUserId(Integer id);

    Double creditsWallet(Integer id, Double money);

    Double debitsWallet(Integer id, Double money);

}
