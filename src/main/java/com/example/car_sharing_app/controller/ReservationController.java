package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.Reservation;
import com.example.car_sharing_app.repository.ReservationRepository;
import com.example.car_sharing_app.request.ReservationRequest;
import com.example.car_sharing_app.response.ReservationResponse;
import com.example.car_sharing_app.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    ReservationService reservationService;
    ReservationRepository reservationRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations/{userId}")
    public List<ReservationResponse> getActiveReservations(@PathVariable Integer userId) {
        return reservationService.getActiveReservations(userId).stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
    }

    @PatchMapping("/cancelReservation")
    public Reservation getReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.cancelReservation(reservationRequest);
    }

    @PostMapping("/newReservation")
    public Reservation addNewReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.addReservation(reservationRequest);
    }

    @PatchMapping("/cancel/{id}")
    public Reservation cancelReservation(@PathVariable Integer id, @RequestBody ReservationRequest reservationRequest) {
        return reservationService.cancelReservation(id, reservationRequest);
    }
}
