package by.maximoc.vacanciesandroid.data.network;

import java.util.Map;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface HhApiInterface {

    @GET("vacancies/{vacancy_id}")
    Single<Vacancy> getVacancyDetail(@Path("vacancy_id") String vacancyId);

    @GET("vacancies?host=jobs.tut.by")
    Single<Vacancies> getVacancies(@Query("text") String keyWord,
                                   @Query("area") String location,
                                   @Query("order_by") String sort,
                                   @Query("period") int period,
                                   @QueryMap Map<String, String> page);

}
