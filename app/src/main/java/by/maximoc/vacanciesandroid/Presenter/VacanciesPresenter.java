package by.maximoc.vacanciesandroid.Presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.maximoc.vacanciesandroid.View.MainActivityView;

public interface VacanciesPresenter extends MvpPresenter<MainActivityView> {
    void getVacancies(String page);
    void onStop();

    int getStopListener();

    void setStopListener(int findLasVisibleItemPosition);
}
