package uca.apps.isi.UbiUCA.Models;


import com.google.gson.annotations.SerializedName;

public class PlaceModel{
    private String name;
    @SerializedName("location")
    private LocationModel location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
