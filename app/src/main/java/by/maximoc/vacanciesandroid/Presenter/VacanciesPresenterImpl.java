package by.maximoc.vacanciesandroid.Presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.HashMap;
import java.util.Map;

import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.View.MainActivityView;
import by.maximoc.vacanciesandroid.Model.VacanciesModelImpl;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class VacanciesPresenterImpl extends MvpBasePresenter<MainActivityView> implements VacanciesPresenter {

    private VacanciesModelImpl model;
    final CompositeDisposable composite = new CompositeDisposable();

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
                        composite.add(d);
                    }

                    @Override
                    public void onNext(Vacancies vacancies) {

                        if (page.equals("0"))
                            getView().showVacancies(vacancies);
                        else
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
    public void onStop() {
        composite.dispose();
    }
}