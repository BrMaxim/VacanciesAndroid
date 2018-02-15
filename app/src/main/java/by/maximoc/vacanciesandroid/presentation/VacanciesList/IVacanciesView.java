package by.maximoc.vacanciesandroid.presentation.VacanciesList;

import android.content.Intent;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.presentation.Base.IBaseView;

interface IVacanciesView extends IBaseView {

    void addDataToAdapter(Vacancies vacancies);

    void startNewActivity(Intent intent);
}
