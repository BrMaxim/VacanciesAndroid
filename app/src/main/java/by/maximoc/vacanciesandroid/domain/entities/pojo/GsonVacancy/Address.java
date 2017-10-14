package by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.MetroStation;

public class Address {

    @SerializedName("building")
    @Expose
    private Object building;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("metro")
    @Expose
    private by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Metro metro;
    @SerializedName("metro_stations")
    @Expose
    private List<by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.MetroStation> metroStations = null;
    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("street")
    @Expose
    private Object street;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("lng")
    @Expose
    private Object lng;

    public Object getBuilding() {
        return building;
    }

    public void setBuilding(Object building) {
        this.building = building;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Metro getMetro() {
        return metro;
    }

    public void setMetro(by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Metro metro) {
        this.metro = metro;
    }

    public List<by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.MetroStation> getMetroStations() {
        return metroStations;
    }

    public void setMetroStations(List<MetroStation> metroStations) {
        this.metroStations = metroStations;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Object getStreet() {
        return street;
    }

    public void setStreet(Object street) {
        this.street = street;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

}