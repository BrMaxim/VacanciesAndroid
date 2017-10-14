package by.maximoc.vacanciesandroid.domain.interactors.detailVacancy;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;

public interface IVacancyDetailInteractor {

    Single<Vacancy> getVacancyDetailModel(String vacancyId);

    String createStringSalary(Salary salary);

    String createStringAddress(Address address);

    boolean isAccessToInternet();

}
