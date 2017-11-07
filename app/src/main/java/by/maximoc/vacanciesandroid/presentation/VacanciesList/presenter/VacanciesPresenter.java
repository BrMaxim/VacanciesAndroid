package by.maximoc.vacanciesandroid.presentation.VacanciesList.presenter;


import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.HashMap;
import java.util.Map;

import by.maximoc.vacanciesandroid.data.dataBase.VacanciesDataBase;
import by.maximoc.vacanciesandroid.data.repositories.VacanciesDataRepository;
import by.maximoc.vacanciesandroid.data.repositories.vacancies.VacanciesDataStoreFactory;
import by.maximoc.vacanciesandroid.domain.interactors.vacanciesList.VacanciesInteractor;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.view.VacancyDetailActivity;
import by.maximoc.vacanciesandroid.presentation.VacanciesList.view.IVacanciesView;
import by.maximoc.vacanciesandroid.utils.Constants;
import by.maximoc.vacanciesandroid.utils.ThrowableResolver;
import io.reactivex.disposables.CompositeDisposable;

import static by.maximoc.vacanciesandroid.utils.Constants.KEY_NUM_MINSK;
import static by.maximoc.vacanciesandroid.utils.Constants.KEY_WORD_VACANCY;


public class VacanciesPresenter extends MvpBasePresenter<IVacanciesView> implements IVacanciesPresenter {

    private VacanciesInteractor interactor;
    private Context context;
    private CompositeDisposable composite = new CompositeDisposable();
    private static final String COUNT_ITEM_PAGE = "per_page";
    private static final String VACANCIES_SORT = "relevance";
    private static final int PERIOD = 30;
    private Map<String, String> countPage;

    public VacanciesPresenter(Context context) {
        this.context = context;
        interactor = new VacanciesInteractor(new VacanciesDataRepository
                (new VacanciesDataStoreFactory(new VacanciesDataBase(), context)));
        countPage = new HashMap<>();
        countPage.put(COUNT_ITEM_PAGE, String.valueOf(Constants.COUNT_PER_PAGE));
    }

    @Override
    public void getVacancies(String page) {
        countPage.put(Constants.PAGE, page);

        interactor.getVacanciesModel(KEY_WORD_VACANCY, KEY_NUM_MINSK, VACANCIES_SORT, PERIOD, countPage)
                .doOnSubscribe(disposable -> {
                    composite.add(disposable);
                    getView().showProgressBar();
                })
                .doAfterTerminate(getView()::hideProgressBar)
                .subscribe(vacancies -> {
                            Log.d("TAG", "Page "+vacancies.getPage());
                    getView().addDataToAdapter(vacancies);},
                        throwable -> getView().showError(ThrowableResolver.handleError(throwable, context)));
    }

    @Override
    public void onStop() {
        if (!composite.isDisposed()) {
            composite.clear();
        }
    }

    @Override
    public void itemClick(String urlVacancy) {
        if (isViewAttached()) {
            getView().startDetailActivity(VacancyDetailActivity.getVacancyIntent(context, urlVacancy));
        }
    }
}