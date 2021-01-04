package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletRepository extends JpaRepository<UserWallet, Integer> {

    UserWallet findUserWalletByUserId(User user);
}
