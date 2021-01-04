package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserWallet;
import com.example.car_sharing_app.service.UserService;
import com.example.car_sharing_app.service.UserWalletService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserWalletController {

    UserWalletService userWalletService;
    UserService userService;

    public UserWalletController(UserWalletService userWalletService, UserService userService) {
        this.userWalletService = userWalletService;
        this.userService = userService;
    }

    @GetMapping("/walletValue/{id}")
    public Double getUserWalletValue(@PathVariable Integer id){
        User user = userService.findById(id);
        UserWallet userWalletByUserId = userWalletService.findUserWalletByUserId(user);
        return userWalletByUserId.getValue();
    }

    @PatchMapping("/credits/{id}")
    public Double creditsUserWallet(@PathVariable Integer id, @RequestBody Double money) {
        User user = userService.findById(id);
        Double newWalletValue = userWalletService.creditsWallet(user, money);
        return newWalletValue;
    }

    @PatchMapping("/debits/{id}")
    public Double debitsUserWallet(@PathVariable Integer id, @RequestBody Double money) {
        User user = userService.findById(id);
        Double newWalletValue = userWalletService.debitsWallet(user, money);
        return newWalletValue;
    }
}
