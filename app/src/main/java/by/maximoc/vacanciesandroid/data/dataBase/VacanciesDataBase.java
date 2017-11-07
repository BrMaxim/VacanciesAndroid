package by.maximoc.vacanciesandroid.data.dataBase;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.utils.Constants;
import io.reactivex.Single;
import io.realm.Realm;

public class VacanciesDataBase implements IVacanciesDataBase {

    private boolean isFirstStart = false;

    @Override
    public boolean isCached() {
        return Realm.getDefaultInstance().where(Vacancies.class).findFirst() != null;
    }

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period,
                                          Map<String, String> page) {
        Vacancies vacancies = Realm.getDefaultInstance().where(Vacancies.class)
                .equalTo(Constants.PAGE, Integer.parseInt(page.get(Constants.PAGE))).findFirst();

        return Single.fromCallable(() -> vacancies);
    }

    @Override
    public void cachedVacancies(Vacancies vacancies) {
        Realm.getDefaultInstance().executeTransactionAsync(realm1 -> {
            if (!isFirstStart) {
                realm1.deleteAll();
                isFirstStart = true;
            }
            realm1.insert(vacancies);
        });
    }
}
