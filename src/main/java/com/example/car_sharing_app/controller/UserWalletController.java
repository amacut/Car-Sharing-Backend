package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserWallet;
import com.example.car_sharing_app.request.WalletUpdateRequest;
import com.example.car_sharing_app.response.UserResponse;
import com.example.car_sharing_app.response.UserWalletHistoryResponse;
import com.example.car_sharing_app.service.UserService;
import com.example.car_sharing_app.service.UserWalletService;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserWalletController {

    UserWalletService userWalletService;
    UserService userService;

    public UserWalletController(UserWalletService userWalletService, UserService userService) {
        this.userWalletService = userWalletService;
        this.userService = userService;
    }

    @GetMapping("/walletValue/{id}")
    public Double getUserWalletValue(@PathVariable Integer id) {
        UserWallet userWalletByUserId = userWalletService.findUserWalletByUserId(id);
        Double value = userWalletByUserId.getValue();
        return value;
    }

    @PatchMapping("/credits/{id}")
    public Double creditsUserWallet(@PathVariable Integer id, @RequestBody WalletUpdateRequest updateRequest) {
        User user = userService.findById(id);
        if (user.getPassword().equals(updateRequest.getPassword())) {
            Double newWalletValue = userWalletService.creditsWallet(id, updateRequest.getValue());
            return newWalletValue;
        } else {
            throw new IllegalStateException("Złe hasło");
        }
    }

    @PatchMapping("/debits/{id}")
    public Double debitsUserWallet(@PathVariable Integer id, @RequestBody Double value) {
        /*Double newWalletValue = userWalletService.debitsWallet(id, value);
        return newWalletValue;
        */
        return null;
    }

    @GetMapping("/history/{id}")
    public Set<UserWalletHistoryResponse> getAll(@PathVariable Integer id) {
        UserWallet userWalletByUserId = userWalletService.findUserWalletByUserId(id);
        return userWalletByUserId.getWalletHistories().stream()
                .map(UserWalletHistoryResponse::new)
                .collect(Collectors.toSet());
    }
}
