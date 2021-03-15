package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.Reservation;
import com.example.car_sharing_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation, Integer> {

//    List<Reservation> findAllByUser(User user);
    List<Reservation> findAllByEndReservationIsBefore(LocalDateTime localDateTime);
    Reservation findByVehicleIdAndUserIdAndEndReservationIsAfter(Integer vehicleId, Integer userId, LocalDateTime localDateTime);
}
