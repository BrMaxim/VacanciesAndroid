package by.maximoc.vacanciesandroid.domain.interactors.vacanciesList;

import java.util.Map;

import javax.inject.Inject;

import by.maximoc.vacanciesandroid.data.IRepository;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public class VacanciesInteractor implements IVacanciesInteractor {

    private final IRepository repository;

    @Inject
    public VacanciesInteractor(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<Vacancies> getVacanciesModel(String keyWord, String location, String sort,
                                               int period, Map<String, String> page) {
        return repository.getVacancies(keyWord, location, sort, period, page);
    }

}
