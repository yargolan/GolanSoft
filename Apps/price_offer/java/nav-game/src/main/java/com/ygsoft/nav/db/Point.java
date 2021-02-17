package com.ygsoft.nav.db;

public class Point {

    private String pointId, pointArea, pointDescription;
    private long pointH, pointW;

    public Point (String id, String area, long h, long w, String description) {
        this.pointH           = h;
        this.pointW           = w;
        this.pointId          = id;
        this.pointArea        = area;
        this.pointDescription = description;
    }


    public String getPointId() {
        return pointId;
    }

    public String getPointArea() {
        return pointArea;
    }

    public String getPointDescription() {
        return pointDescription;
    }

    public long getPointH() {
        return pointH;
    }

    public long getPointW() {
        return pointW;
    }
}
