package by.maximoc.vacanciesandroid.presentation.VacanciesList.view;

import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;

public interface IVacanciesView extends MvpView {

    void addDataToAdapter(Vacancies vacancies);

    void showError(String throwable);

    void showProgressBar();

    void hideProgressBar();

    void startDetailActivity(Intent intent);
}
