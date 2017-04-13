package by.maximoc.vacanciesandroid.Model;

import android.content.Context;

import java.util.Map;

import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Service.HhApiInterface;
import by.maximoc.vacanciesandroid.Service.ServiceFactory;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VacanciesModelImpl implements VacanciesModel {
    private Context context;
    private HhApiInterface getApi(){
        return new ServiceFactory().getHhApiInterface();
    }

    public VacanciesModelImpl(Context context){
        this.context = context;
    }

    @Override
    public Observable<Vacancies> getVacanciesModel(String keyWord, String location, String sort, int period, Map<String, String> page) {
        return getApi().getVacancies(keyWord, location, sort, period, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
