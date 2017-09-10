package by.maximoc.vacanciesandroid.utils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        long DATABASE_VER = 1;
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(DATABASE_VER)
                .name("VacanciesDB")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}