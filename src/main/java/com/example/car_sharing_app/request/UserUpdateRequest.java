package com.example.car_sharing_app.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String birthDate;
    private String country;
    private String street;
    private String houseNoFlatNo;
    private String postcode;
    private String city;
}
