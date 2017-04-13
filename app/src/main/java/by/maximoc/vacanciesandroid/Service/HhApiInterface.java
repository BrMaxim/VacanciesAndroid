package by.maximoc.vacanciesandroid.Service;

import java.util.Map;

import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface HhApiInterface {
    @GET("vacancies?host=jobs.tut.by")
    Observable<Vacancies> getVacancies(@Query("text") String keyWord,
                                       @Query("area") String location,
                                       @Query("order_by") String sort,
                                       @Query("period") int period,
                                       @QueryMap Map<String, String> page);
}
