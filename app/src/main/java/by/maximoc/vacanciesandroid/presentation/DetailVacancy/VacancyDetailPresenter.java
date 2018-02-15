package by.maximoc.vacanciesandroid.presentation.DetailVacancy;


import javax.inject.Inject;

import by.maximoc.vacanciesandroid.domain.interactors.detailVacancy.VacancyDetailInteractor;
import by.maximoc.vacanciesandroid.presentation.Base.BasePresenter;
import by.maximoc.vacanciesandroid.utils.ErrorHandler;

public class VacancyDetailPresenter extends BasePresenter<IVacancyDetailView> {

    private static final int EMPTY_LIST = 0;
    @Inject
    VacancyDetailInteractor interactor;

    void getDetailInfo(String idVacancy) {
        interactor.getVacancyDetailModel(idVacancy)
                .to(this::wrapObservable)
                .subscribe(vacancy -> {
                    getView().showDetail(vacancy);
                    if (vacancy.getKeySkills().size() != EMPTY_LIST) {
                        getView().addSkill(vacancy);
                    }
                }, throwable -> getView().showError(ErrorHandler.handleError(throwable, context)));
    }
}
