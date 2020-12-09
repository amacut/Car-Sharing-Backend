package com.example.car_sharing_app.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String gender;
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
