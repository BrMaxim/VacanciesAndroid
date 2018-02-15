package by.maximoc.vacanciesandroid.data.dataBaseRepository;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public interface IDataBaseRepository {

    boolean isCached();

    Single<Vacancies> getVacancies(Map<String, String> page);

    void cachedVacancies(Vacancies vacancies);
}
