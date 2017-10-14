package by.maximoc.vacanciesandroid.domain.interactors.vacanciesList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.repositories.IVacanciesRepository;
import by.maximoc.vacanciesandroid.repositories.dataBase.VacanciesDB;
import io.reactivex.Single;
import io.realm.Realm;

import static io.realm.Realm.getDefaultInstance;

public class VacanciesInteractor implements IVacanciesInteractor {
    private Context context;
    private Realm realm;
    private IVacanciesRepository vacanciesRepository;

    public VacanciesInteractor(Context context, IVacanciesRepository vacanciesRepository) {
        this.context = context;
        this.vacanciesRepository = vacanciesRepository;
    }

    @Override
    public Single<Vacancies> getVacanciesModel(String keyWord, String location, String sort,
                                               int period, Map<String, String> page) {
        return vacanciesRepository.getVacancies(keyWord, location, sort, period, page)
                .onErrorResumeNext(throwable -> new VacanciesDB().getVacancies(keyWord, location, sort, period, page));
    }

    @Override
    public boolean isAccessToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public void setVacanciesToDb(Vacancies allVacancies) {
        realm = getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> {
            realm1.deleteAll();
            Vacancies vacancies = new Vacancies();
            realm1.insertOrUpdate(allVacancies);
        });
    }

}
