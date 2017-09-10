package by.maximoc.vacanciesandroid.Presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.ui.MainActivityView;

public interface VacanciesPresenter extends MvpPresenter<MainActivityView> {
    void getVacancies(String page);
    void onStop();
    void onDestroy(Vacancies vacancies);
    boolean isAccessToInternet();

    void itemClick(String urlVacancy);
}
