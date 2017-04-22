package by.maximoc.vacanciesandroid.DataBase;

import io.realm.RealmList;
import io.realm.RealmObject;

public class VacanciesDataBase extends RealmObject {

    private RealmList<Item> items;

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }
}
