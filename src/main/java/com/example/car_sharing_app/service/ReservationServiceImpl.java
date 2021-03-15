package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Reservation;
import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.model.VehicleStatus;
import com.example.car_sharing_app.repository.ReservationRepository;
import com.example.car_sharing_app.repository.UserRepository;
import com.example.car_sharing_app.repository.VehicleRepository;
import com.example.car_sharing_app.request.ReservationRequest;
import com.example.car_sharing_app.response.ReservationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    UserRepository userRepository;
    VehicleRepository vehicleRepository;
    UserService userService;
    VehicleService vehicleService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, VehicleRepository vehicleRepository, UserService userService, VehicleService vehicleService) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @Override
    public List<Reservation> getActiveReservations(Integer userId) {
        User user = userService.findById(userId);
        return user.getReservations().stream()
                .filter(reservation -> reservation.getEndReservation().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation addReservation(ReservationRequest reservationRequest) {

        User user = userService.findById(Integer.parseInt(reservationRequest.getUserId()));
        Vehicle vehicle = vehicleService.findById(reservationRequest.getVehicleId());
        Integer reservationTime = reservationRequest.getReservationTime();
        if (reservationTime > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startReservation = LocalDateTime.now();
            LocalDateTime endReservation = startReservation.plusHours(reservationTime);

            String formatStartReservation = startReservation.format(formatter);
            String formatEndReservation = endReservation.format(formatter);

            Reservation newReservation = new Reservation();
            newReservation.setUser(user);
            newReservation.setVehicle(vehicle);
            newReservation.setStartReservation(LocalDateTime.parse(formatStartReservation, formatter));
            newReservation.setEndReservation(LocalDateTime.parse(formatEndReservation, formatter));
            newReservation.setFinished(false);

            vehicle.setVehicleStatus(VehicleStatus.RESERVED);
            vehicleRepository.save(vehicle);
            user.getReservations().add(newReservation);
            userRepository.save(user);

            return newReservation;
        } else {
            throw new IllegalStateException("reservationTime < 0");
        }
    }

    @Override
    public Reservation cancelReservation(ReservationRequest reservationRequest) {
        Integer vehicleId = reservationRequest.getVehicleId();

        Reservation reservation = reservationRepository.findByVehicleIdAndUserIdAndEndReservationIsAfter( vehicleId, Integer.parseInt(reservationRequest.getUserId()), LocalDateTime.now());
        System.out.println(reservation.toString());
        reservation.setEndReservation(LocalDateTime.now());
        reservation.setFinished(true);

        Vehicle vehicle = vehicleService.findById(vehicleId);
        vehicle.setVehicleStatus(VehicleStatus.FREE);
        vehicleRepository.save(vehicle);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation cancelReservation(Integer id, ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Reservation " + id + " does not exist"
                ));
        reservation.setEndReservation(LocalDateTime.now());

        Vehicle vehicle = vehicleService.findById(reservationRequest.getVehicleId());
        vehicle.setVehicleStatus(VehicleStatus.FREE);
        vehicleRepository.save(vehicle);

        return reservationRepository.save(reservation);
    }
}
