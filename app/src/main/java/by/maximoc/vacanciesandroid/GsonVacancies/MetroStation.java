package by.maximoc.vacanciesandroid.GsonVacancies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetroStation {

    @SerializedName("line_name")
    @Expose
    private String lineName;
    @SerializedName("station_id")
    @Expose
    private String stationId;
    @SerializedName("line_id")
    @Expose
    private String lineId;
    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("station_name")
    @Expose
    private String stationName;
    @SerializedName("lng")
    @Expose
    private Float lng;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

}