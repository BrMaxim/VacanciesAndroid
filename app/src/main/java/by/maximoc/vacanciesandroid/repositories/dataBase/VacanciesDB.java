package by.maximoc.vacanciesandroid.repositories.dataBase;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.repositories.IVacanciesRepository;
import io.reactivex.Single;

import static io.realm.Realm.getDefaultInstance;

public class VacanciesDB implements IVacanciesRepository {

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return Single.fromCallable(() -> getDefaultInstance().where(Vacancies.class).findFirst());
    }
}
