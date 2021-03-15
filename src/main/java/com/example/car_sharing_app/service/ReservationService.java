package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Reservation;
import com.example.car_sharing_app.request.ReservationRequest;
import com.example.car_sharing_app.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    List<Reservation> getActiveReservations(Integer userId);

    Reservation addReservation(ReservationRequest reservationRequest);

    Reservation cancelReservation(ReservationRequest reservationRequest);

    Reservation cancelReservation(Integer id, ReservationRequest reservationRequest);

}
