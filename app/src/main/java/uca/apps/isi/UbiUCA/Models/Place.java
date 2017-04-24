package uca.apps.isi.UbiUCA.Models;


import io.realm.RealmObject;

public class Place extends RealmObject{
    private String name;
    private uca.apps.isi.UbiUCA.Models.Location location;

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