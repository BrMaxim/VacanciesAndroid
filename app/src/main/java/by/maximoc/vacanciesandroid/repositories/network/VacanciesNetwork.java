package by.maximoc.vacanciesandroid.repositories.network;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.repositories.IVacanciesRepository;
import by.maximoc.vacanciesandroid.service.ServiceFactory;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VacanciesNetwork implements IVacanciesRepository {

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return ServiceFactory.hhApiInterface.getVacancies(keyWord, location, sort, period, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
