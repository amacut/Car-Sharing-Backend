package com.example.car_sharing_app.request;

import lombok.Data;


@Data
public class VehicleUpdateRequest {

    private Integer vehicleModelId;
    private String registration;
    private Double maxFuel;
    private Double currentFuel;
    private Double maxRange;
    private Double currentRange;
    private String latitude;
    private String longitude;

}
