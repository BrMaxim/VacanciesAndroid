package by.maximoc.vacanciesandroid.Presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.Model.VacancyDetailModelImpl;
import by.maximoc.vacanciesandroid.ui.VacancyDetailView;
import io.reactivex.disposables.CompositeDisposable;

public class VacancyDetailPresenterImpl extends MvpBasePresenter<VacancyDetailView> implements VacancyDetailPresenter {

    private VacancyDetailModelImpl model;
    final CompositeDisposable composite = new CompositeDisposable();

    public VacancyDetailPresenterImpl(Context context) {
        model = new VacancyDetailModelImpl(context);
    }

    @Override
    public void getDetailInfo(String idVacancy) {

        model.getVacancyDetailModel(idVacancy)
                .subscribe(vacancy -> getView().showDetail(vacancy),
                        throwable -> {
                            if (!model.isAccessToInternet())
                                getView().showError();
                        }, () -> {
                        }, disposable -> composite.add(disposable));
    }

    @Override
    public String getAddressString(Address address) {
        return model.createStringAddress(address);
    }

    @Override
    public String getSalaryString(Salary salary) {
        return model.createStringSalary(salary);
    }

    @Override
    public void onStop() {
        composite.dispose();
    }
}
