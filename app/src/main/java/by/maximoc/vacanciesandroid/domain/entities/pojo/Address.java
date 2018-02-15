package by.maximoc.vacanciesandroid.domain.entities.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Address extends RealmObject {

    @SerializedName("building")
    @Expose
    private String building;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("metro")
    @Expose
    private Metro metro;
    @SerializedName("metro_stations")
    @Expose
    private RealmList<MetroStation> metroStations = null;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;
    @SerializedName("id")
    @Expose
    private String id;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    public RealmList<MetroStation> getMetroStations() {
        return metroStations;
    }

    public void setMetroStations(RealmList<MetroStation> metroStations) {
        this.metroStations = metroStations;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}