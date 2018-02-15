package by.maximoc.vacanciesandroid.presentation.DI;

import javax.inject.Singleton;

import by.maximoc.vacanciesandroid.presentation.DetailVacancy.VacancyDetailPresenter;
import by.maximoc.vacanciesandroid.presentation.VacanciesList.VacanciesPresenter;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(VacanciesPresenter vacanciesPresenter);

    void inject(VacancyDetailPresenter vacancyDetailPresenter);
}
