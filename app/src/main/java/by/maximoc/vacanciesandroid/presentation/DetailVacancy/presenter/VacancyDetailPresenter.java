package by.maximoc.vacanciesandroid.presentation.DetailVacancy.presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.domain.interactors.detailVacancy.VacancyDetailInteractor;
import by.maximoc.vacanciesandroid.data.repositories.VacancyDetailNetwork;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.view.IVacancyDetailView;
import by.maximoc.vacanciesandroid.utils.ThrowableResolver;
import io.reactivex.disposables.CompositeDisposable;

public class VacancyDetailPresenter extends MvpBasePresenter<IVacancyDetailView> implements IVacancyDetailPresenter {

    private VacancyDetailInteractor interactor;
    private CompositeDisposable composite = new CompositeDisposable();
    private Context context;

    public VacancyDetailPresenter(Context context) {
        this.context = context;
        interactor = new VacancyDetailInteractor(context, new VacancyDetailNetwork());
    }

    @Override
    public void getDetailInfo(String idVacancy) {

        interactor.getVacancyDetailModel(idVacancy)
                .doOnSubscribe(disposable -> {
                    composite.add(disposable);
                    getView().showProgressBar();
                })
                .doAfterTerminate(getView()::hideProgressBar)
                .subscribe(vacancy -> getView().showDetail(vacancy),
                        throwable -> getView().showError(ThrowableResolver.handleError(throwable, context)));
    }

    @Override
    public String getAddressString(Address address) {
        return interactor.createStringAddress(address);
    }

    @Override
    public String getSalaryString(Salary salary) {
        return interactor.createStringSalary(salary);
    }

    @Override
    public void onStop() {
        if (!composite.isDisposed()) {
            composite.clear();
        }
    }

}
