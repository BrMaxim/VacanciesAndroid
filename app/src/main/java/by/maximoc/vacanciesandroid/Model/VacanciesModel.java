package by.maximoc.vacanciesandroid.Model;


import java.util.Map;

import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;
import io.reactivex.Observable;

public interface VacanciesModel {
    Observable<Vacancies> getVacanciesModel(String keyWord, String location, String sort, int period, Map<String, String> page);

    boolean isAccessToInternet();
}
