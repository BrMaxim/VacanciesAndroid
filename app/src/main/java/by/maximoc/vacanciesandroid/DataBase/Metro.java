package by.maximoc.vacanciesandroid.DataBase;

import io.realm.RealmObject;

public class Metro extends RealmObject {

    private String stationName;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
