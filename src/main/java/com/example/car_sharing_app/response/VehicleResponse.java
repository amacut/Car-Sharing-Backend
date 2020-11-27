package com.example.car_sharing_app.response;

import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.vehicleHelper.VehicleHelper;
import lombok.Data;

@Data
public class VehicleResponse {

    private Integer id;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleModel;
    private Double drivingPrice;
    private Double stopOverPrice;
    private Double distancePrice;
    private String registration;
    private String currentFuel;
    private String currentRange;
    private String latitude;
    private String longitude;

    public VehicleResponse(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.vehicleType = vehicle.getVehicleModel().getVehicleType().getName();
        this.vehicleBrand = vehicle.getVehicleModel().getBrand();
        this.vehicleModel = vehicle.getVehicleModel().getModel();
        this.drivingPrice = vehicle.getVehicleModel().getVehicleType().getDrivingPrice();
        this.stopOverPrice = vehicle.getVehicleModel().getVehicleType().getStopOverPrice();
        this.distancePrice = vehicle.getVehicleModel().getVehicleType().getDistancePrice();
        this.registration = vehicle.getRegistration();
        this.currentFuel = VehicleHelper.getRoundedFuel(vehicle.getCurrentFuel(), vehicle.getMaxFuel());
        this.currentRange = VehicleHelper.getRoundedRange(vehicle.getCurrentRange(), vehicle.getMaxRange());
        this.latitude = vehicle.getLatitude();
        this.longitude = vehicle.getLongitude();
    }
}
