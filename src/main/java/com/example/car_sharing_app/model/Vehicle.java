package com.example.car_sharing_app.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "model_id")
    private VehicleModel vehicleModel;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus vehicleStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", insertable = false)
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "vehicle")
    private List<UserRental> userRentalList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(Double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public Double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(Double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public Double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    public Double getCurrentRange() {
        return currentRange;
    }

    public void setCurrentRange(Double currentRange) {
        this.currentRange = currentRange;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<UserRental> getRentalList() {
        return userRentalList;
    }

    public void setRentalList(List<UserRental> userRentalList) {
        this.userRentalList = userRentalList;
    }
}
