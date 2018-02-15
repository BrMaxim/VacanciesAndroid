package by.maximoc.vacanciesandroid.presentation.Base;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IBaseView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError(String message);
}
