package com.example.car_sharing_app.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserHelper {

    public static String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
