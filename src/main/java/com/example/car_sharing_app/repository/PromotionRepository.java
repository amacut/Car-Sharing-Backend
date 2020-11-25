package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
