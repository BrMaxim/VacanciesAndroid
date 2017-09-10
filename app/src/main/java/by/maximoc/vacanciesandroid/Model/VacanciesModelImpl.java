package by.maximoc.vacanciesandroid.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Map;

import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Service.HhApiInterface;
import by.maximoc.vacanciesandroid.Service.ServiceFactory;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

import static io.realm.Realm.getDefaultInstance;

public class VacanciesModelImpl implements VacanciesModel {
    private Context context;
    private Realm realm;

    private HhApiInterface getApi() {
        return new ServiceFactory().getHhApiInterface();
    }

    public VacanciesModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Vacancies> getVacanciesModel(String keyWord, String location, String sort,
                                                   int period, Map<String, String> page) {
        return getApi().getVacancies(keyWord, location, sort, period, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    return getVacanciesFromDb();
                });
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

    private Observable<Vacancies> getVacanciesFromDb() {
        return Observable.fromCallable(() -> getDefaultInstance().where(Vacancies.class).findFirst());
    }

}
