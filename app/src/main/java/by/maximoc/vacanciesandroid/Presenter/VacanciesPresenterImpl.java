package by.maximoc.vacanciesandroid.Presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.HashMap;
import java.util.Map;

import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Model.VacanciesModelImpl;
import by.maximoc.vacanciesandroid.ui.MainActivityView;
import by.maximoc.vacanciesandroid.utils.Constants;
import io.reactivex.disposables.CompositeDisposable;

import static by.maximoc.vacanciesandroid.utils.Constants.KEY_NUM_MINSK;
import static by.maximoc.vacanciesandroid.utils.Constants.KEY_WORD_VACANCY;


public class VacanciesPresenterImpl extends MvpBasePresenter<MainActivityView> implements VacanciesPresenter {

    private VacanciesModelImpl model;
    private CompositeDisposable composite = new CompositeDisposable();

    public VacanciesPresenterImpl(Context context) {
        model = new VacanciesModelImpl(context);
    }

    @Override
    public void getVacancies(String page) {
        Map<String, String> countPage = new HashMap<>();
        countPage.put("per_page", String.valueOf(Constants.COUNT_PER_PAGE));
        countPage.put("page", page);

        model.getVacanciesModel(KEY_WORD_VACANCY, KEY_NUM_MINSK, "relevance", 30, countPage)
                .subscribe(vacancies -> getView().addDataToAdapter(vacancies),
                        throwable -> getView().showError(), () -> {
                        }, disposable -> composite.add(disposable));
    }

    @Override
    public void onStop() {
        if (!composite.isDisposed())
            composite.clear();
    }

    @Override
    public void onDestroy(Vacancies vacancies) {
        if (model.isAccessToInternet() && vacancies != null) {
            model.setVacanciesToDb(vacancies);
        }
        if (!composite.isDisposed())
            composite.dispose();
    }

    @Override
    public boolean isAccessToInternet() {
        return model.isAccessToInternet();
    }

    @Override
    public void itemClick(String urlVacancy) {
        if (isViewAttached())
            getView().startDetailActivity(urlVacancy);
    }
}