package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Reservation;
import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.model.VehicleStatus;
import com.example.car_sharing_app.repository.ReservationRepository;
import com.example.car_sharing_app.repository.VehicleRepository;
import com.example.car_sharing_app.request.VehicleCoordinatesRequest;
import com.example.car_sharing_app.request.VehicleUpdateRequest;
import com.example.car_sharing_app.response.VehicleResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;
    UserService userService;
    ReservationRepository reservationRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserService userService, ReservationRepository reservationRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
        this.reservationRepository = reservationRepository;
    }

    private void checkVehiclesStatus() {
        List<Reservation> endedReservations = reservationRepository.findAllByEndReservationIsBefore(LocalDateTime.now());

        for (Reservation reservation : endedReservations) {
            Vehicle vehicle = reservation.getVehicle();
            if (vehicle.getVehicleStatus().equals(VehicleStatus.RESERVED) && reservation.getEndReservation().isBefore(LocalDateTime.now()) && !reservation.getFinished()) {
                System.out.println(vehicle.getVehicleStatus().equals(VehicleStatus.RESERVED));
                System.out.println(vehicle.getId() + " " + vehicle.getVehicleStatus());
                vehicle.setVehicleStatus(VehicleStatus.FREE);
                reservation.setFinished(true);
                vehicleRepository.save(vehicle);
            }
        }
    }

    @Override
    public List<Vehicle> findAll(Integer userId) {
        checkVehiclesStatus();
        User user = userService.findById(userId);
        List<Vehicle> vehicles = user.getReservations().stream()
                .filter(reservation -> reservation.getEndReservation().isAfter(LocalDateTime.now()))
                .map(Reservation::getVehicle)
                .collect(Collectors.toList());

        List<Vehicle> collect = vehicleRepository.findAll().stream()
                .filter(vehicle -> vehicle.getVehicleStatus().equals(VehicleStatus.FREE))
                .collect(Collectors.toList());
        collect.addAll(vehicles);
        /*List<Vehicle> finalCollect = collect.stream().distinct().collect(Collectors.toList());
        for (Vehicle vehicle : finalCollect) {
            System.out.println(vehicle.getId());
        }*/
        return collect;

    }

    @Override
    public Vehicle findById(Integer id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Vehicle " + id + " does not exist"
        ));
    }

    @Override
    public Vehicle addVehicle(VehicleUpdateRequest vehicleUpdateRequest) {
        Vehicle newVehicle = new Vehicle();
        this.setVehicle(newVehicle, vehicleUpdateRequest);
        return newVehicle;
    }

    @Override
    public VehicleResponse changeVehicleDetails(Integer id, VehicleCoordinatesRequest vehicleCoordinatesRequest) {
        Vehicle vehicleToUpdate = findById(id);
        vehicleToUpdate.setLatitude(vehicleCoordinatesRequest.getLatitude());
        vehicleToUpdate.setLongitude(vehicleCoordinatesRequest.getLongitude());
        System.out.println(vehicleCoordinatesRequest.getLatitude());
        System.out.println(vehicleCoordinatesRequest.getLongitude());
        vehicleRepository.save(vehicleToUpdate);
        return new VehicleResponse(vehicleToUpdate);
    }

    @Override
    public Vehicle deleteVehicle(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(new Vehicle());
        vehicle.setDeletedAt(LocalDateTime.now());
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    private void setVehicle(Vehicle vehicle, VehicleUpdateRequest vehicleUpdateRequest) {
        vehicle.setRegistration(vehicleUpdateRequest.getRegistration());
        vehicle.setCurrentFuel(vehicleUpdateRequest.getCurrentFuel());
        vehicle.setLatitude(vehicleUpdateRequest.getLatitude());
        vehicle.setLongitude(vehicleUpdateRequest.getLongitude());
        vehicleRepository.save(vehicle);
    }
}
