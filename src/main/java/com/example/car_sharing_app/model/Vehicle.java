package com.example.car_sharing_app.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "model_id", nullable = false)
    private Integer vehicleModelId;

    @Column(name = "registration", nullable = false)
    private String registration;

    @Column(name = "max_fuel", nullable = false)
    private Double maxFuel;

    @Column(name = "current_fuel", nullable = false)
    private Double currentFuel;

    @Column(name = "max_range", nullable = false)
    private Double maxRange;

    @Column(name = "current_range", nullable = false)
    private Double currentRange;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", insertable = false)
    private LocalDateTime deletedAt;
}
