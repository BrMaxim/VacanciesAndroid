package by.maximoc.vacanciesandroid.presentation.VacanciesList.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.maximoc.vacanciesandroid.presentation.VacanciesList.view.IVacanciesView;

public interface IVacanciesPresenter extends MvpPresenter<IVacanciesView> {

    void getVacancies(String page);

    void onStop();

    void itemClick(String urlVacancy);
}
