package by.maximoc.vacanciesandroid.data.repositories.vacancies;

import java.util.Map;

import by.maximoc.vacanciesandroid.data.dataBase.IVacanciesDataBase;
import by.maximoc.vacanciesandroid.data.network.ServiceFactory;
import by.maximoc.vacanciesandroid.data.repositories.IVacanciesDataRepository;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VacanciesCloudStorage implements IVacanciesDataRepository {

    private final IVacanciesDataBase iVacanciesDataBase;

    public VacanciesCloudStorage(IVacanciesDataBase iVacanciesDataBase) {
        this.iVacanciesDataBase = iVacanciesDataBase;
    }

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return ServiceFactory.hhApiInterface.getVacancies(keyWord, location, sort, period, page)
                .doOnSuccess(iVacanciesDataBase::cachedVacancies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
