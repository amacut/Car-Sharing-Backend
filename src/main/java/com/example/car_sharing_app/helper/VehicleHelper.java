package com.example.car_sharing_app.helper;

import com.example.car_sharing_app.model.Vehicle;

import java.text.DecimalFormat;

public class VehicleHelper {
    private static DecimalFormat df = new DecimalFormat("#");

    public static Double updateCurrentFuel(Vehicle vehicle, Double distance) {

        Double lostFuel = getLostFuel(vehicle, distance);

        Double currentFuelInPercent = vehicle.getCurrentFuel();
        Double tankCapacity = vehicle.getVehicleModel().getTankCapacity();

        double currentFuel = (currentFuelInPercent * tankCapacity)/100;

        double updatedCurrentFuel = currentFuel - lostFuel;

        double updatedFuelInTankInPercent = (updatedCurrentFuel / tankCapacity) * 100;

        return updatedFuelInTankInPercent;
    }

    private static Double getLostFuel(Vehicle vehicle, Double distance) {
        Double averageFuelConsumption = vehicle.getVehicleModel().getAverageFuelConsumption();
        Double lostFuel = (distance/1000 * averageFuelConsumption) / 100;
        return lostFuel;
    }

    public static String getCurrentRange(Vehicle vehicle) {
        Double tankCapacity = vehicle.getVehicleModel().getTankCapacity();
        Double averageFuelConsumption = vehicle.getVehicleModel().getAverageFuelConsumption();
        Double currentFuel = vehicle.getCurrentFuel();

        double fuel = (currentFuel * tankCapacity) / 100;
        double currentRange = (fuel / averageFuelConsumption) * 100;

        return df.format(currentRange);
    }

    public static String getFormatCurrentFuel(Double currentFuel) {
        return df.format(currentFuel);
    }



   /* public static String getMaxRange(Double tankCapacity, Double averageFuelConsumption) {
        Double maxRange = (tankCapacity / averageFuelConsumption) * 100;
        return df.format(maxRange);
    }

    public static Double getLostFuel(Double kilometers, Double averageFuelConsumption) {
        Double lostFuel = (averageFuelConsumption * kilometers) / 100;
        return lostFuel;
    }

    public static Double getCurrentFuel(Double lostFuel, Double tankCapacity) {
        double currentFuel = tankCapacity - lostFuel;
        double currentFuelPercent = (currentFuel / tankCapacity) * 100;
        return currentFuelPercent;
    }

    public static String getCurrentRange(Double currentFuel, Double maxRange) {
        double currentRange = (maxRange * currentFuel) / 100;
        return df.format(currentRange);
    }

   */
}
