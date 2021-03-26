package com.yg.apps.cars.data;


public class DbWrapper {

    private final Object object;

    public DbWrapper(Garage garage) {
        this.object = garage;
    }

    public DbWrapper(Maintenance maintenance) {
        this.object = maintenance;
    }


    public Garage getGarage() {
        return (Garage)object;
    }



    public Maintenance getMaintenance() {
        return (Maintenance) object;
    }



    void addToDb() {

    }
}


