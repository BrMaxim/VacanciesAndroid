package by.maximoc.vacanciesandroid.Model;

import android.content.Context;

import by.maximoc.vacanciesandroid.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.Service.HhApiInterface;
import by.maximoc.vacanciesandroid.Service.ServiceFactory;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VacancyDetailModelImpl implements VacancyDetailModel {
    private Context context;

    private HhApiInterface getApi() {
        return new ServiceFactory().getHhApiInterface();
    }

    public VacancyDetailModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Vacancy> getVacancyDetailModel(String vacancyId) {
        return getApi().getVacancyDetail(vacancyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public String createStringSalary(Salary salary) {
        String salaryStr = "";
        if (salary.getFrom() != null)
            salaryStr = "От " + salary.getFrom() + " ";
        if (salary.getTo() != null)
            salaryStr = salaryStr + "До " + salary.getTo();
        salaryStr = salaryStr + " " + salary.getCurrency();
        return salaryStr;
    }

    @Override
    public String createStringAddress(Address address) {
        String addressStr = "";
        if (address.getStreet() != null)
            addressStr = address.getStreet().toString();
        if (address.getBuilding() != null)
            addressStr = addressStr + " " + address.getBuilding();
        return addressStr;
    }
}
