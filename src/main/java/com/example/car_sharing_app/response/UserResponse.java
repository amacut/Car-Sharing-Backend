package com.example.car_sharing_app.response;

import com.example.car_sharing_app.helper.UserHelper;
import com.example.car_sharing_app.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
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
    private Double walletValue;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.birthDate = UserHelper.dateToString(user.getBirthDate());
        this.country = user.getCountry();
        this.street = user.getStreet();
        this.houseNoFlatNo = user.getHouseNoFlatNo();
        this.postcode = user.getPostcode();
        this.city = user.getCity();
        this.walletValue = user.getUserWallet().getValue();
    }
}
