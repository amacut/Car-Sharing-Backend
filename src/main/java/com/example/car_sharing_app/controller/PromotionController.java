package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.Promotion;
import com.example.car_sharing_app.response.PromotionResponse;
import com.example.car_sharing_app.service.PromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PromotionController {

    PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/promotions")
    public List<PromotionResponse> findAll() {
        return promotionService.findAll().stream()
                .map(PromotionResponse::new)
                .collect(Collectors.toList());
    }
}
