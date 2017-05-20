package by.maximoc.vacanciesandroid.Model;

import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Vacancy;
import io.reactivex.Observable;

public interface VacancyDetailModel {
    Observable<Vacancy> getVacancyDetailModel(String vacancyId);
    String createStringSalary(Salary salary);
    String createStringAddress(Address address);

    boolean isAccessToInternet();

}
