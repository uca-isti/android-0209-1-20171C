package uca.apps.isi.UbiUCA.Models;

import io.realm.RealmObject;

public class Location extends RealmObject{
    private double lat;
    private double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Location() {
    }

    public Double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
}
