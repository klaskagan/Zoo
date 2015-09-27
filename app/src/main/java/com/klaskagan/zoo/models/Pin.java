package com.klaskagan.zoo.models;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/27.
 */
public class Pin {

    private String name;
    private String latitude;
    private String longitude;

    public Pin() {
    }

    public Pin(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
