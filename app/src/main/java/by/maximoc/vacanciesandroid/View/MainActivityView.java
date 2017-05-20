package by.maximoc.vacanciesandroid.View;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;

public interface MainActivityView extends MvpView {
    void addDataToAdapter(Vacancies vacancies);
    void showError();

    void startDetailActivity(String urlVacancy);
}
