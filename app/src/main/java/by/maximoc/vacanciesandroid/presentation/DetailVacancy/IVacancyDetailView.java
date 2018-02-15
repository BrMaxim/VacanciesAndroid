package by.maximoc.vacanciesandroid.presentation.DetailVacancy;

import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.presentation.Base.IBaseView;

interface IVacancyDetailView extends IBaseView {

    void showDetail(Vacancy vacancy);

    void addSkill(Vacancy vacancy);

}
