package by.maximoc.vacanciesandroid.presentation.VacanciesList.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.presentation.VacanciesList.view.IVacanciesView;

public interface IVacanciesPresenter extends MvpPresenter<IVacanciesView> {

    void getVacancies(String page);

    void onDestroy(Vacancies vacancies);

    boolean isAccessToInternet();

    void itemClick(String urlVacancy);
}
