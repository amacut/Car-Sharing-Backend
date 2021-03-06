package com.example.car_sharing_app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vehicle_models")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "model_id")
    private List<Vehicle> vehicles;

    @OneToOne
    @JoinColumn(name = "type_id")
    private VehicleType vehicleType;


    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "tank_capacity")
    private Double tankCapacity;

    @Column(name = "average_fuel_consumption")
    private Double averageFuelConsumption;


}
