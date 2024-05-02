package Helpers;

import Models.Vehicle;

import java.util.Comparator;

public class VehicleEnginePowerComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle vehicleOne, Vehicle vehicleTwo) {
        return vehicleOne.getEnginePower().compareTo(vehicleTwo.getEnginePower());
    }
}