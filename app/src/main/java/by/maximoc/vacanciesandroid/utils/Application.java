package by.maximoc.vacanciesandroid.utils;

import by.maximoc.vacanciesandroid.presentation.DI.AppComponent;
import by.maximoc.vacanciesandroid.presentation.DI.AppModule;
import by.maximoc.vacanciesandroid.presentation.DI.DaggerAppComponent;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "VacanciesLocalStorage";
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDataBase();
        appComponent = buildComponent();
    }

    private void initDataBase(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(DATABASE_VERSION)
                .name(DATABASE_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}