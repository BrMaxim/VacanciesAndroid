package by.maximoc.vacanciesandroid.data.repositories;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public interface IVacanciesDataRepository {

    Single<Vacancies> getVacancies(String keyWord, String location, String sort,
                                   int period, Map<String, String> page);
}
