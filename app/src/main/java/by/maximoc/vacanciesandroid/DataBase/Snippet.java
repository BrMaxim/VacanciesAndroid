package by.maximoc.vacanciesandroid.DataBase;

import io.realm.RealmObject;

public class Snippet extends RealmObject {

    private String requirement;

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
