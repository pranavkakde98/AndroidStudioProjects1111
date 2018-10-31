package com.example.hp.afinal;

public class mLocation {
    Double Latitude,Longitude;

    public mLocation(Double latitude, Double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public mLocation() {
    }

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }
}
