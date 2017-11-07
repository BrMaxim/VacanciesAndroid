package by.maximoc.vacanciesandroid.domain.interactors.vacanciesList;

import java.util.Map;

import by.maximoc.vacanciesandroid.data.repositories.IVacanciesDataRepository;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public class VacanciesInteractor implements IVacanciesInteractor {
    private IVacanciesDataRepository vacanciesRepository;

    public VacanciesInteractor(IVacanciesDataRepository vacanciesRepository) {
        this.vacanciesRepository = vacanciesRepository;
    }

    @Override
    public Single<Vacancies> getVacanciesModel(String keyWord, String location, String sort,
                                               int period, Map<String, String> page) {
        return vacanciesRepository.getVacancies(keyWord, location, sort, period, page);
    }

}
