package com.example.car_sharing_app.response;

import com.example.car_sharing_app.helper.DateHelper;
import com.example.car_sharing_app.model.Reservation;
import lombok.Data;

@Data
public class ReservationResponse {

    private Integer id;
    private String startReservation;
    private String endReservation;
    private String registration;
    private String brand;
    private String model;
    private String vehicleType;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.startReservation = DateHelper.dateTimeToString(reservation.getStartReservation());
        this.endReservation = DateHelper.dateTimeToString(reservation.getEndReservation());
        this.registration = reservation.getVehicle().getRegistration();
        this.brand = reservation.getVehicle().getVehicleModel().getBrand();
        this.model = reservation.getVehicle().getVehicleModel().getModel();
        this.vehicleType = reservation.getVehicle().getVehicleModel().getVehicleType().getName();
    }
}
