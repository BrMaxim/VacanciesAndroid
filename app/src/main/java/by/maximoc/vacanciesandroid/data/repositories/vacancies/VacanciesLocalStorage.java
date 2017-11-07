package by.maximoc.vacanciesandroid.data.repositories.vacancies;

import java.util.Map;

import by.maximoc.vacanciesandroid.data.dataBase.IVacanciesDataBase;
import by.maximoc.vacanciesandroid.data.repositories.IVacanciesDataRepository;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import io.reactivex.Single;

public class VacanciesLocalStorage implements IVacanciesDataRepository {

    private final IVacanciesDataBase iVacanciesDataBase;

    public VacanciesLocalStorage(IVacanciesDataBase iVacanciesDataBase) {
        this.iVacanciesDataBase = iVacanciesDataBase;
    }

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return iVacanciesDataBase.getVacancies(keyWord, location, sort, period, page);
    }
}
