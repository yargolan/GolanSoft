package com.ygsoft.apps.camping;

public class Vehicle {

    private int    vehicleIndex;
    private String vehicleMake, vehicleModel;


    public Vehicle(String vehicleMake, String vehicleModel, int vehicleIndex) {
        this.vehicleMake  = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleIndex = vehicleIndex;
    }


    public String getVehicleMake() {
        return vehicleMake;
    }



    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }



    public String getVehicleModel() {
        return vehicleModel;
    }



    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }


    public int getVehicleIndex() {
        return vehicleIndex;
    }



    public void setVehicleIndex(int vehicleIndex) {
        this.vehicleIndex = vehicleIndex;
    }

    @Override
    public String toString() {
        return String.format ("%s %s (%d)", vehicleMake, vehicleModel, vehicleIndex);
    }
}
