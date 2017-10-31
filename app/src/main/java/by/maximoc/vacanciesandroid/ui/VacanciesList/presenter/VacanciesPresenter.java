package by.maximoc.vacanciesandroid.ui.VacanciesList.presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.HashMap;
import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.domain.interactors.vacanciesList.VacanciesInteractor;
import by.maximoc.vacanciesandroid.repositories.network.VacanciesNetwork;
import by.maximoc.vacanciesandroid.ui.VacanciesList.view.IVacanciesView;
import by.maximoc.vacanciesandroid.utils.Constants;
import io.reactivex.disposables.CompositeDisposable;

import static by.maximoc.vacanciesandroid.utils.Constants.KEY_NUM_MINSK;
import static by.maximoc.vacanciesandroid.utils.Constants.KEY_WORD_VACANCY;


public class VacanciesPresenter extends MvpBasePresenter<IVacanciesView> implements IVacanciesPresenter {

    private VacanciesInteractor model;
    private CompositeDisposable composite = new CompositeDisposable();
    private static final String COUNT_ITEM_PAGE = "per_page";
    private static final String PAGE = "page";
    private static final String VACANCIES_SORT = "relevance";
    private static final int PERIOD = 30;

    public VacanciesPresenter(Context context) {
        model = new VacanciesInteractor(context, new VacanciesNetwork());
    }

    @Override
    public void getVacancies(String page) {
        Map<String, String> countPage = new HashMap<>();
        countPage.put(COUNT_ITEM_PAGE, String.valueOf(Constants.COUNT_PER_PAGE));
        countPage.put(PAGE, page);

        model.getVacanciesModel(KEY_WORD_VACANCY, KEY_NUM_MINSK, VACANCIES_SORT, PERIOD, countPage)
                .subscribe(vacancies -> getView().addDataToAdapter(vacancies),
                        throwable -> getView().showError());
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