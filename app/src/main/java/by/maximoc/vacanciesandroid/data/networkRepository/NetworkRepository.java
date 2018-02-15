package by.maximoc.vacanciesandroid.data.networkRepository;

import java.util.Map;

import javax.inject.Inject;

import by.maximoc.vacanciesandroid.data.mapper.IVacanciesMapper;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetworkRepository implements INetworkRepository {

    private final ApiInterface apiInterface;
    private final IVacanciesMapper mapper;

    @Inject
    public NetworkRepository(ApiInterface apiInterface, IVacanciesMapper mapper) {
        this.apiInterface = apiInterface;
        this.mapper = mapper;
    }

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return apiInterface.getVacancies(keyWord, location, sort, period, page)
                .doOnSuccess(vacancies -> mapper.formatSalaryItems(vacancies.getItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Vacancy> getVacancyDetail(String vacancyId) {
        return apiInterface.getVacancyDetail(vacancyId)
                .doOnSuccess(vacancy -> {
                    vacancy.setFormatSalary(mapper.formatSalary(vacancy.getSalary()));
                    vacancy.setFormatAddress(mapper.formatAddress(vacancy.getAddress()));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
