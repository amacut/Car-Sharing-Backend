package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.UserWalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletHistoryRepository extends JpaRepository<UserWalletHistory, Integer> {
}
