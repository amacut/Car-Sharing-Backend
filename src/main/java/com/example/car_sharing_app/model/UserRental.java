package com.example.car_sharing_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
