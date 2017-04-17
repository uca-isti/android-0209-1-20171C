package uca.apps.isi.UbiUCA.Models;


import android.location.Geocoder;

public class Place {
    private String name;
    private Location location;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
}
