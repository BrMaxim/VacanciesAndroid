package by.maximoc.vacanciesandroid.View;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.maximoc.vacanciesandroid.GsonVacancy.Vacancy;

public interface VacancyDetailView extends MvpView {
    void showDetail(Vacancy vacancy);
}
