package by.maximoc.vacanciesandroid.presentation.DI;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import by.maximoc.vacanciesandroid.BuildConfig;
import by.maximoc.vacanciesandroid.data.IRepository;
import by.maximoc.vacanciesandroid.data.Repository;
import by.maximoc.vacanciesandroid.data.dataBaseRepository.DataBaseRepository;
import by.maximoc.vacanciesandroid.data.dataBaseRepository.IDataBaseRepository;
import by.maximoc.vacanciesandroid.data.networkRepository.ApiInterface;
import by.maximoc.vacanciesandroid.data.networkRepository.INetworkRepository;
import by.maximoc.vacanciesandroid.data.networkRepository.NetworkRepository;
import by.maximoc.vacanciesandroid.data.mapper.IVacanciesMapper;
import by.maximoc.vacanciesandroid.data.mapper.VacanciesMapper;
import by.maximoc.vacanciesandroid.domain.interactors.detailVacancy.IVacancyDetailInteractor;
import by.maximoc.vacanciesandroid.domain.interactors.detailVacancy.VacancyDetailInteractor;
import by.maximoc.vacanciesandroid.domain.interactors.vacanciesList.IVacanciesInteractor;
import by.maximoc.vacanciesandroid.domain.interactors.vacanciesList.VacanciesInteractor;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.VacancyDetailPresenter;
import by.maximoc.vacanciesandroid.presentation.VacanciesList.VacanciesPresenter;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String URL = "https://api.hh.ru/";
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    static IDataBaseRepository provideDataBaseRepository(DataBaseRepository dataBaseRepository) {
        return dataBaseRepository;
    }

    @Provides
    static INetworkRepository provideNetworkRepository(NetworkRepository networkRepository) {
        return networkRepository;
    }

    @Provides
    static IRepository provideRepository(Repository repository) {
        return repository;
    }

    @Provides
    static VacancyDetailPresenter provideVacancyDetailPresenter(VacancyDetailPresenter vacancyDetailPresenter) {
        return vacancyDetailPresenter;
    }

    @Provides
    static VacanciesPresenter provideVacanciesPresenter(VacanciesPresenter vacanciesPresenter) {
        return vacanciesPresenter;
    }

    @Provides
    static IVacanciesInteractor provideVacanciesInteractor(VacanciesInteractor vacanciesInteractor) {
        return vacanciesInteractor;
    }

    @Provides
    static IVacancyDetailInteractor provideVacancyDetailInteractor(VacancyDetailInteractor detailInteractor) {
        return detailInteractor;
    }

    @Provides
    static IVacanciesMapper provideVacanciesMapper(VacanciesMapper vacanciesMapper) {
        return vacanciesMapper;
    }

    @Singleton
    @Provides
    static ApiInterface provideApiInterface() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }
}
