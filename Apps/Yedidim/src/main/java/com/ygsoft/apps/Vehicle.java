package com.ygsoft.apps;

public class Vehicle {

    private String vehicleVendor, vehicleModel;

    public Vehicle(String vehicleVendor, String vehicleModel) {
        this.vehicleModel  = vehicleModel;
        this.vehicleVendor = vehicleVendor;
    }


    public String getVehicleVendor() {
        return vehicleVendor;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleVendor(String vehicleVendor) {
        this.vehicleVendor = vehicleVendor;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getName() {
        return this.vehicleVendor + " " + this.getVehicleModel();
    }
}
