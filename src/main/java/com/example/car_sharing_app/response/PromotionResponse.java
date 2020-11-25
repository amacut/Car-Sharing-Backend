package com.example.car_sharing_app.response;

import com.example.car_sharing_app.model.Promotion;
import lombok.Data;

@Data
public class PromotionResponse {

    private Integer id;
    private String name;
    private String code;
    private String description;

    public PromotionResponse(Promotion promotion) {
        this.id = promotion.getId();
        this.name = promotion.getName();
        this.code = promotion.getCode();
        this.description = promotion.getDescription();
    }
}
