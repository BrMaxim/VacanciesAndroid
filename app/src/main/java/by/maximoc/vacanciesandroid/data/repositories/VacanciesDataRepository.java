package by.maximoc.vacanciesandroid.data.repositories;

import java.util.Map;

import by.maximoc.vacanciesandroid.data.repositories.vacancies.VacanciesDataStoreFactory;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public class VacanciesDataRepository implements IVacanciesDataRepository {

    private final VacanciesDataStoreFactory storeFactory;

    public VacanciesDataRepository(VacanciesDataStoreFactory storeFactory) {
        this.storeFactory = storeFactory;
    }

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return storeFactory.create().getVacancies(keyWord, location, sort, period, page);
    }

}
