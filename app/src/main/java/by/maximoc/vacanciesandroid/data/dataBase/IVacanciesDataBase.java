package by.maximoc.vacanciesandroid.data.dataBase;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public interface IVacanciesDataBase {

    boolean isCached();

    Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page);

    void cachedVacancies(Vacancies vacancies);
}
