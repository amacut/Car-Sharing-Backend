package com.example.car_sharing_app.vehicleHelper;

import com.example.car_sharing_app.model.VehicleModel;

import java.text.DecimalFormat;

public class VehicleHelper {
    private static DecimalFormat df = new DecimalFormat("#");

    public static String getRoundedFuel(Double currentFuel, Double maxFuel) {
        double result = (currentFuel / maxFuel)*100;
        return df.format(result);
    }

    public static String getRoundedRange(Double currentRange, Double maxRange) {
        double result = maxRange - currentRange;
        return df.format(result);
    }


}
