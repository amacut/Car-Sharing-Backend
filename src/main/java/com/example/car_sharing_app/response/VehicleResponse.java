package com.example.car_sharing_app.response;

import com.example.car_sharing_app.model.Vehicle;
import lombok.Data;

@Data
public class VehicleResponse {

    private Integer id;
    private Integer vehicleModelId;
    private String registration;
    private Double maxFuel;
    private Double currentFuel;
    private Double maxRange;
    private Double currentRange;
    private String latitude;
    private String longitude;

    public VehicleResponse(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.vehicleModelId = vehicle.getVehicleModelId();
        this.registration = vehicle.getRegistration();
        this.maxFuel = vehicle.getMaxFuel();
        this.currentFuel = vehicle.getMaxFuel();
        this.maxRange = vehicle.getMaxRange();
        this.currentRange = vehicle.getCurrentRange();
        this.latitude = vehicle.getLatitude();
        this.longitude = vehicle.getLongitude();
    }
}
