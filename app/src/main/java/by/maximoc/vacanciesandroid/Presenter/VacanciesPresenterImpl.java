package by.maximoc.vacanciesandroid.Presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.HashMap;
import java.util.Map;

import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Model.VacanciesModelImpl;
import by.maximoc.vacanciesandroid.View.MainActivityView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class VacanciesPresenterImpl extends MvpBasePresenter<MainActivityView> implements VacanciesPresenter {

    private VacanciesModelImpl model;
    int stopScrollListener;

    public VacanciesPresenterImpl(Context context) {
        model = new VacanciesModelImpl(context);
    }

    @Override
    public void getVacancies(String page) {
        Map<String, String> countPage = new HashMap<>();
        countPage.put("per_page", String.valueOf(Constants.COUNT_PER_PAGE));
        countPage.put("page", page);

        model.getVacanciesModel("android", "1002", "relevance", 30, countPage)
                .subscribe(new Observer<Vacancies>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Vacancies vacancies) {
                            getView().addDataToAdapter(vacancies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public int getStopListener() {
        return stopScrollListener;
    }

    @Override
    public void setStopListener(int findLasVisibleItemPosition) {
        stopScrollListener = findLasVisibleItemPosition;
    }

    @Override
    public void onStop() {
    }
}