package by.maximoc.vacanciesandroid.DataBase;

import io.realm.RealmObject;

public class Address extends RealmObject {

    private Metro metro;

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }
}
