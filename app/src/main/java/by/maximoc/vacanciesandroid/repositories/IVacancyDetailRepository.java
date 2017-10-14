package by.maximoc.vacanciesandroid.repositories;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;

public interface IVacancyDetailRepository {

    Single<Vacancy> getVacancyDetail(String vacancyId);
}
