package com.example.car_sharing_app.response;

import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.vehicleHelper.VehicleHelper;
import lombok.Data;

@Data
public class VehicleResponse {

    private Integer id;
    private Integer vehicleModelId;
    private String registration;
    private String currentFuel;
    private String currentRange;
    private String latitude;
    private String longitude;

    public VehicleResponse(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.vehicleModelId = vehicle.getVehicleModelId();
        this.registration = vehicle.getRegistration();
        this.currentFuel = VehicleHelper.getRoundedFuel(vehicle.getCurrentFuel(), vehicle.getMaxFuel());
        this.currentRange = VehicleHelper.getRoundedRange(vehicle.getCurrentRange(),vehicle.getMaxRange());
        this.latitude = vehicle.getLatitude();
        this.longitude = vehicle.getLongitude();
    }
}
