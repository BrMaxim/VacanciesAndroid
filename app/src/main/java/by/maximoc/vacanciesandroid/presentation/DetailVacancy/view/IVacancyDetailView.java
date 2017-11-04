package by.maximoc.vacanciesandroid.presentation.DetailVacancy.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;

public interface IVacancyDetailView extends MvpView {
    void showDetail(Vacancy vacancy);

    void showError(String error);

    void showProgressBar();

    void hideProgressBar();
}
