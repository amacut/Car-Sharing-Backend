package com.example.car_sharing_app.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals_history")
public class UserRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "driving_time")
    private Integer drivingTime;

    @Column(name = "driving_price")
    private Double drivingPrice;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "distance_price")
    private Double distancePrice;

    @Column(name = "stop_over_time")
    private Integer stopOverTime;

    @Column(name = "stop_over_price")
    private Double stopOverPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(Integer drivingTime) {
        this.drivingTime = drivingTime;
    }

    public Double getDrivingPrice() {
        return drivingPrice;
    }

    public void setDrivingPrice(Double drivingPrice) {
        this.drivingPrice = drivingPrice;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDistancePrice() {
        return distancePrice;
    }

    public void setDistancePrice(Double distancePrice) {
        this.distancePrice = distancePrice;
    }

    public Integer getStopOverTime() {
        return stopOverTime;
    }

    public void setStopOverTime(Integer stopOverTime) {
        this.stopOverTime = stopOverTime;
    }

    public Double getStopOverPrice() {
        return stopOverPrice;
    }

    public void setStopOverPrice(Double stopOverPrice) {
        this.stopOverPrice = stopOverPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
