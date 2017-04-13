package by.maximoc.vacanciesandroid.View;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;

public interface MainActivityView extends MvpView {
    void showVacancies(Vacancies vacancies);
    void addDataToAdapter(Vacancies vacancies);
    void showError();
}
