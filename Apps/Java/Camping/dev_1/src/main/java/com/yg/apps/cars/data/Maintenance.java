package com.yg.apps.cars.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Maintenance {


    @Expose
    @SerializedName("type")
    private final String type;

    @Expose
    @SerializedName("garage")
    private final String garage;

    @Expose
    @SerializedName("date")
    private final String date;

    @Expose
    @SerializedName("details")
    private final List<String> details;



    public Maintenance(String date, String garage, String type, List<String> details) {
        this.type    = type;
        this.date    = date;
        this.garage  = garage;
        this.details = details;
    }


    public String getName() {
        return type;
    }


    public String getType() {
        return type;
    }


    public String getGarage() {
        return garage;
    }


    public String getDate() {
        return date;
    }


    public List<String> getDetails() {
        return details;
    }


    public void addToDb(Maintenance maintenance) {
        DbWrapperMaintenance dbWrapperMaintenance = new DbWrapperMaintenance(maintenance);
        dbWrapperMaintenance.addToDb();
    }
}

















