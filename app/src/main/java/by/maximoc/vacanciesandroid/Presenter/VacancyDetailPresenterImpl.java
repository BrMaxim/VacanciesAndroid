package by.maximoc.vacanciesandroid.Presenter;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.Model.VacancyDetailModelImpl;
import by.maximoc.vacanciesandroid.View.VacancyDetailView;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class VacancyDetailPresenterImpl extends MvpBasePresenter<VacancyDetailView> implements VacancyDetailPresenter {

    private VacancyDetailModelImpl model;
    final CompositeDisposable composite = new CompositeDisposable();

    public VacancyDetailPresenterImpl(Context context) {
        model = new VacancyDetailModelImpl(context);
    }

    @Override
    public void getDetailInfo(String idVacancy) {
        model.getVacancyDetailModel(idVacancy)
                .subscribe(new Observer<Vacancy>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(Vacancy vacancy) {
                        getView().showDetail(vacancy);
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
