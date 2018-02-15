package by.maximoc.vacanciesandroid.data.networkRepository;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;

public interface INetworkRepository {

    Single<Vacancy> getVacancyDetail(String vacancyId);

    Single<Vacancies> getVacancies(String keyWord, String location, String sort,
                                   int period, Map<String, String> page);
}
