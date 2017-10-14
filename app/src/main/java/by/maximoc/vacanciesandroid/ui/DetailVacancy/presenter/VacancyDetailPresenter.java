package by.maximoc.vacanciesandroid.ui.DetailVacancy.presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.domain.interactors.detailVacancy.VacancyDetailInteractor;
import by.maximoc.vacanciesandroid.repositories.network.VacancyDetailNetwork;
import by.maximoc.vacanciesandroid.ui.DetailVacancy.view.IVacancyDetailView;
import io.reactivex.disposables.CompositeDisposable;

public class VacancyDetailPresenter extends MvpBasePresenter<IVacancyDetailView> implements IVacancyDetailPresenter {

    private VacancyDetailInteractor interactor;
    final CompositeDisposable composite = new CompositeDisposable();

    public VacancyDetailPresenter(Context context) {
        interactor = new VacancyDetailInteractor(context, new VacancyDetailNetwork());
    }

    @Override
    public void getDetailInfo(String idVacancy) {

        interactor.getVacancyDetailModel(idVacancy)
                .subscribe(vacancy -> getView().showDetail(vacancy),
                        throwable -> {
                            if (!interactor.isAccessToInternet()) {
                                getView().showError();
                            }
                        });
    }

    @Override
    public String getAddressString(Address address) {
        return interactor.createStringAddress(address);
    }

    @Override
    public String getSalaryString(Salary salary) {
        return interactor.createStringSalary(salary);
    }

}
