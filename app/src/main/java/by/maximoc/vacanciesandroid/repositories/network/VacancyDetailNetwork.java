package by.maximoc.vacanciesandroid.repositories.network;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.repositories.IVacancyDetailRepository;
import by.maximoc.vacanciesandroid.service.ServiceFactory;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VacancyDetailNetwork implements IVacancyDetailRepository {

    @Override
    public Single<Vacancy> getVacancyDetail(String vacancyId) {
        return ServiceFactory.hhApiInterface.getVacancyDetail(vacancyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
