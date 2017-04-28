package by.maximoc.vacanciesandroid.Presenter;


import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.HashMap;
import java.util.Map;

import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Model.VacanciesModelImpl;
import by.maximoc.vacanciesandroid.View.MainActivityView;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static by.maximoc.vacanciesandroid.Constants.KEY_WORD_VACANCY;


public class VacanciesPresenterImpl extends MvpBasePresenter<MainActivityView> implements VacanciesPresenter {

    private VacanciesModelImpl model;
    CompositeDisposable composite = new CompositeDisposable();

    public VacanciesPresenterImpl(Context context) {
        model = new VacanciesModelImpl(context);
    }

    @Override
    public void getVacancies(String page) {
        Map<String, String> countPage = new HashMap<>();
        countPage.put("per_page", String.valueOf(Constants.COUNT_PER_PAGE));
        countPage.put("page", page);

        model.getVacanciesModel(KEY_WORD_VACANCY, "1002", "relevance", 30, countPage)
                .subscribe(new Observer<Vacancies>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(Vacancies vacancies) {
                        getView().addDataToAdapter(vacancies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "Error Presenter");
                        getView().showError();
                        Vacancies vacancies = model.getDataOnDb();
                        if (vacancies.getItems() != null)
                            getView().addDataToAdapter(vacancies);
                        composite.dispose();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onStop() {
        composite.clear();
    }

    @Override
    public void onDestroy(Vacancies vacancies) {
        if (model.isAccessToInternet() == true) {
            model.writeDataToDb(vacancies);
        }
    }

    @Override
    public boolean isAccessToInternet() {
        return model.isAccessToInternet();
    }
}