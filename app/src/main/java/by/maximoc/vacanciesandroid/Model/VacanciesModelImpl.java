package by.maximoc.vacanciesandroid.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.Map;

import by.maximoc.vacanciesandroid.DataBase.Address;
import by.maximoc.vacanciesandroid.DataBase.Area;
import by.maximoc.vacanciesandroid.DataBase.Employer;
import by.maximoc.vacanciesandroid.DataBase.Item;
import by.maximoc.vacanciesandroid.DataBase.Metro;
import by.maximoc.vacanciesandroid.DataBase.Salary;
import by.maximoc.vacanciesandroid.DataBase.Snippet;
import by.maximoc.vacanciesandroid.DataBase.VacanciesDataBase;
import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Service.HhApiInterface;
import by.maximoc.vacanciesandroid.Service.ServiceFactory;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class VacanciesModelImpl implements VacanciesModel {
    private Context context;
    private Realm realm;

    private HhApiInterface getApi() {
        return new ServiceFactory().getHhApiInterface();
    }

    public VacanciesModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Vacancies> getVacanciesModel(String keyWord, String location, String sort,
                                                   int period, Map<String, String> page) {
        return getApi().getVacancies(keyWord, location, sort, period, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public boolean isAccessToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public Vacancies getDataOnDb() {
        realm = Realm.getDefaultInstance();
        VacanciesDataBase realmObject = realm.where(VacanciesDataBase.class).findFirst();
        Vacancies vacancies = new Vacancies();

        if (realmObject != null) {
            ArrayList<by.maximoc.vacanciesandroid.Gson.GsonVacancies.Item> itemGs = new ArrayList<>();

            for (int i = 0; i < realmObject.getItems().size(); i++) {
                by.maximoc.vacanciesandroid.Gson.GsonVacancies.Item item = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Item();
                by.maximoc.vacanciesandroid.Gson.GsonVacancies.Snippet snippetGs = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Snippet();
                snippetGs.setRequirement(realmObject.getItems().get(i).getSnippet().getRequirement());
                item.setSnippet(snippetGs);

                item.setName(realmObject.getItems().get(i).getName());

                item.setId(realmObject.getItems().get(i).getId());

                item.setPublishedAt(realmObject.getItems().get(i).getPublishedAt());

                by.maximoc.vacanciesandroid.Gson.GsonVacancies.Area area = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Area();
                area.setName(realmObject.getItems().get(i).getArea().getName());
                item.setArea(area);

                by.maximoc.vacanciesandroid.Gson.GsonVacancies.Employer employer = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Employer();
                employer.setName(realmObject.getItems().get(i).getEmployer().getName());
                item.setEmployer(employer);

                if (realmObject.getItems().get(i).getAddress() != null
                        && realmObject.getItems().get(i).getAddress().getMetro() != null) {
                    by.maximoc.vacanciesandroid.Gson.GsonVacancies.Address address = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Address();
                    by.maximoc.vacanciesandroid.Gson.GsonVacancies.Metro metro = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Metro();
                    metro.setStationName(realmObject.getItems().get(i).getAddress().getMetro().getStationName());
                    address.setMetro(metro);
                    item.setAddress(address);
                }

                if (realmObject.getItems().get(i).getSalary() != null) {
                    by.maximoc.vacanciesandroid.Gson.GsonVacancies.Salary salary = new by.maximoc.vacanciesandroid.Gson.GsonVacancies.Salary();
                    salary.setCurrency(realmObject.getItems().get(i).getSalary().getCurrency());
                    if (realmObject.getItems().get(i).getSalary().getFrom() != null)
                        salary.setFrom(realmObject.getItems().get(i).getSalary().getFrom());
                    if (realmObject.getItems().get(i).getSalary().getTo() != null)
                        salary.setTo(realmObject.getItems().get(i).getSalary().getTo());
                }
                itemGs.add(item);
            }
            vacancies.setItems(itemGs);
        }
        return vacancies;
    }

    public void writeDataToDb(Vacancies vacancies) {
        realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> {
            realm1.deleteAll();
            VacanciesDataBase db = realm1.createObject(VacanciesDataBase.class);

            for (int i = 0; i < vacancies.getItems().size(); i++) {
                Item item = realm1.createObject(Item.class);
                item.setName(vacancies.getItems().get(i).getName());
                item.setPublishedAt(vacancies.getItems().get(i).getPublishedAt());
                item.setId(vacancies.getItems().get(i).getId());

                Snippet snippetDb = realm1.createObject(Snippet.class);
                snippetDb.setRequirement(vacancies.getItems().get(i).getSnippet().getRequirement());
                item.setSnippet(snippetDb);

                if (vacancies.getItems().get(i).getAddress() != null
                        && vacancies.getItems().get(i).getAddress().getMetro() != null) {
                    Metro metroDb = realm1.createObject(Metro.class);
                    metroDb.setStationName(vacancies.getItems().get(i).getAddress().getMetro().getStationName());
                    Address addressDb = realm1.createObject(Address.class);
                    addressDb.setMetro(metroDb);
                    item.setAddress(addressDb);
                }

                Area areaDb = realm1.createObject(Area.class);
                areaDb.setName(vacancies.getItems().get(i).getArea().getName());
                item.setArea(areaDb);

                Employer employerDb = realm1.createObject(Employer.class);
                employerDb.setName(vacancies.getItems().get(i).getEmployer().getName());
                item.setEmployer(employerDb);

                if (vacancies.getItems().get(i).getSalary() != null) {
                    Salary salaryDb = realm1.createObject(Salary.class);
                    salaryDb.setCurrency(vacancies.getItems().get(i).getSalary().getCurrency());
                    if (vacancies.getItems().get(i).getSalary().getFrom() != null)
                        salaryDb.setFrom(vacancies.getItems().get(i).getSalary().getFrom());
                    if (vacancies.getItems().get(i).getSalary().getTo() != null)
                        salaryDb.setTo(vacancies.getItems().get(i).getSalary().getTo().toString());
                    item.setSalary(salaryDb);
                }
                db.getItems().add(item);
            }
        });
    }
}
