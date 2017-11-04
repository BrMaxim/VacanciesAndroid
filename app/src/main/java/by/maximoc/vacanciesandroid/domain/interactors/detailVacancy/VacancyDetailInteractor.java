package by.maximoc.vacanciesandroid.domain.interactors.detailVacancy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Salary;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.repositories.IVacancyDetailRepository;
import io.reactivex.Single;

public class VacancyDetailInteractor implements IVacancyDetailInteractor {

    private Context context;
    private IVacancyDetailRepository vacancyDetailRepository;

    public VacancyDetailInteractor(Context context, IVacancyDetailRepository vacancyDetailRepository) {
        this.context = context;
        this.vacancyDetailRepository = vacancyDetailRepository;
    }

    @Override
    public Single<Vacancy> getVacancyDetailModel(String vacancyId) {
        return vacancyDetailRepository.getVacancyDetail(vacancyId);
    }

    @Override
    public boolean isAccessToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
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
