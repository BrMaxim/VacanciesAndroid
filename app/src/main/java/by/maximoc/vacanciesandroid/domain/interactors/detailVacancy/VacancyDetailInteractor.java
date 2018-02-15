package by.maximoc.vacanciesandroid.domain.interactors.detailVacancy;

import javax.inject.Inject;

import by.maximoc.vacanciesandroid.data.IRepository;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;

public class VacancyDetailInteractor implements IVacancyDetailInteractor {

    private final IRepository repository;

    @Inject
    public VacancyDetailInteractor(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<Vacancy> getVacancyDetailModel(String vacancyId) {
        return repository.getVacancyDetail(vacancyId);
    }

}
