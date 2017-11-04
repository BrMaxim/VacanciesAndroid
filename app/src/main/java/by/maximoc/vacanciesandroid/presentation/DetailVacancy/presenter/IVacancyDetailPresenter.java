package by.maximoc.vacanciesandroid.presentation.DetailVacancy.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.view.IVacancyDetailView;

public interface IVacancyDetailPresenter extends MvpPresenter<IVacancyDetailView> {

    void getDetailInfo(String idVacancy);

    String getAddressString(Address address);

    String getSalaryString(Salary salary);

    void onStop();
}
