package com.example.car_sharing_app.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EntityScan
@Table(name = "vehicle_types")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "type_id")
    private List<VehicleModel> vehicleModels;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "driving_price", nullable = false)
    private Double drivingPrice;

    @Column(name = "stop_over_price", nullable = false)
    private Double stopOverPrice;

    @Column(name = "distance_price", nullable = false)
    private Double distancePrice;


}

