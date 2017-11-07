package by.maximoc.vacanciesandroid.domain.interactors.vacanciesList;


import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public interface IVacanciesInteractor {

    Single<Vacancies> getVacanciesModel(String keyWord, String location, String sort,
                                        int period, Map<String, String> page);
}
