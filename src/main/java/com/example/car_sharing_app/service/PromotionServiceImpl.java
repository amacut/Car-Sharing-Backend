package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Promotion;
import com.example.car_sharing_app.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    private PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }
}
