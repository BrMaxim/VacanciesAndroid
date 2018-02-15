package by.maximoc.vacanciesandroid.presentation.VacanciesList;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import by.maximoc.vacanciesandroid.domain.interactors.vacanciesList.VacanciesInteractor;
import by.maximoc.vacanciesandroid.presentation.Base.BasePresenter;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.VacancyDetailActivity;
import by.maximoc.vacanciesandroid.utils.Constants;
import by.maximoc.vacanciesandroid.utils.ErrorHandler;

import static by.maximoc.vacanciesandroid.utils.Constants.KEY_NUM_MINSK;
import static by.maximoc.vacanciesandroid.utils.Constants.KEY_WORD_VACANCY;


public class VacanciesPresenter extends BasePresenter<IVacanciesView> {

    private static final String COUNT_ITEM_PAGE = "per_page";
    private static final String VACANCIES_SORT = "relevance";
    private static final int PERIOD = 30;
    private final Map<String, String> countPage;
    @Inject
    VacanciesInteractor interactor;


    VacanciesPresenter() {
        countPage = new HashMap<>();
        countPage.put(COUNT_ITEM_PAGE, String.valueOf(Constants.COUNT_PER_PAGE));
    }

    public void getVacancies(String page) {
        countPage.put(Constants.PAGE, page);

        interactor.getVacanciesModel(KEY_WORD_VACANCY, KEY_NUM_MINSK, VACANCIES_SORT, PERIOD, countPage)
                .to(this::wrapObservable)
                .subscribe(vacancies -> getView().addDataToAdapter(vacancies),
                        throwable -> getView().showError(ErrorHandler.handleError(throwable, context)));
    }

    void itemClick(String urlVacancy, String nameVacancy) {
        getView().startNewActivity(VacancyDetailActivity.getVacancyIntent(context, urlVacancy, nameVacancy));
    }

}