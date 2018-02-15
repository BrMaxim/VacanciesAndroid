package by.maximoc.vacanciesandroid.data;

import java.util.Map;

import javax.inject.Inject;

import by.maximoc.vacanciesandroid.data.dataBaseRepository.IDataBaseRepository;
import by.maximoc.vacanciesandroid.data.mapper.IVacanciesMapper;
import by.maximoc.vacanciesandroid.data.networkRepository.INetworkRepository;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;

public class Repository implements IRepository {

    private final INetworkRepository networkRepository;
    private final IDataBaseRepository dataBaseRepository;
    private final IVacanciesMapper mapper;

    @Inject
    public Repository(INetworkRepository networkRepository, IDataBaseRepository dataBaseRepository,
                      IVacanciesMapper mapper) {
        this.networkRepository = networkRepository;
        this.dataBaseRepository = dataBaseRepository;
        this.mapper = mapper;
    }

    @Override
    public Single<Vacancy> getVacancyDetail(String vacancyId) {
        return networkRepository.getVacancyDetail(vacancyId);
    }

    @Override
    public Single<Vacancies> getVacancies(String keyWord, String location, String sort, int period,
                                          Map<String, String> page) {
        return networkRepository.getVacancies(keyWord, location, sort, period, page)
                .doOnSuccess(this::cachedVacancies)
                .onErrorResumeNext(throwable -> {
                    if (dataBaseRepository.isCached()) {
                        return dataBaseRepository.getVacancies(page);
                    } else {
                        return Single.error(throwable);
                    }
                }).doOnSuccess(vacancies -> mapper.formatDateItems(vacancies.getItems()));
    }

    @Override
    public boolean isCached() {
        return dataBaseRepository.isCached();
    }

    @Override
    public void cachedVacancies(Vacancies vacancies) {
        dataBaseRepository.cachedVacancies(vacancies);
    }
}
