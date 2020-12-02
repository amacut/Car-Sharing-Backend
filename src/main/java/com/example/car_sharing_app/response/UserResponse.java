package com.example.car_sharing_app.response;

import com.example.car_sharing_app.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate birthDate;
    private String country;
    private String street;
    private String houseNoFlatNo;
    private String postcode;
    private String city;

    public UserResponse(User user) {
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.birthDate = user.getBirthDate();
        this.country = user.getCountry();
        this.street = user.getStreet();
        this.houseNoFlatNo = user.getHouseNoFlatNo();
        this.postcode = user.getPostcode();
        this.city = user.getCity();
    }
}
