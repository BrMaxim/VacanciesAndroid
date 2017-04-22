package by.maximoc.vacanciesandroid.DataBase;

import io.realm.RealmObject;

public class Area extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
