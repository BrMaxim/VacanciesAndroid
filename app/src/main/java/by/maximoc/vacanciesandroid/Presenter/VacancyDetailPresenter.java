package by.maximoc.vacanciesandroid.Presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.maximoc.vacanciesandroid.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.View.VacancyDetailView;

public interface VacancyDetailPresenter extends MvpPresenter<VacancyDetailView> {

    void getDetailInfo(String idVacancy);
    void onStop();
    String getAddressString(Address address);
    String getSalaryString(Salary salary);
}
