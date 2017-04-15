package by.maximoc.vacanciesandroid.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.CommonMethod;
import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.Presenter.VacancyDetailPresenter;
import by.maximoc.vacanciesandroid.Presenter.VacancyDetailPresenterImpl;
import by.maximoc.vacanciesandroid.R;

public class VacancyDetailActivity extends MvpActivity<VacancyDetailView, VacancyDetailPresenter> implements VacancyDetailView {

    private TextView nameVacancy, salaryVacancy, cityVacancy, experienceVacancy, scheduleVacancy,
            companyVacancy, addressVacancy, subwayVacancy, descriptionVacancy;
    private String idVacancy;
    private LinearLayout allVacancyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_detail);

        nameVacancy = (TextView) findViewById(R.id.name_vacancy);
        salaryVacancy = (TextView) findViewById(R.id.salary_vacancy);
        cityVacancy = (TextView) findViewById(R.id.city_vacancy);
        experienceVacancy = (TextView) findViewById(R.id.experience_vacancy);
        scheduleVacancy = (TextView) findViewById(R.id.schedule_vacancy);
        companyVacancy = (TextView) findViewById(R.id.company_vacancy);
        addressVacancy = (TextView) findViewById(R.id.address_vacancy);
        subwayVacancy = (TextView) findViewById(R.id.subway_vacancy);
        descriptionVacancy = (TextView) findViewById(R.id.descriptions_vacancy);
        allVacancyView = (LinearLayout) findViewById(R.id.all_vacancy_view);

        allVacancyView.setVisibility(View.GONE);

        idVacancy = getIntent().getStringExtra(Constants.URL_VACANCY);

        if (presenter != null)
            presenter.getDetailInfo(idVacancy);
    }

    @NonNull
    @Override
    public VacancyDetailPresenterImpl createPresenter() {
        return new VacancyDetailPresenterImpl(this);
    }

    @Override
    public void showDetail(Vacancy vacancy) {

        nameVacancy.setText(vacancy.getName());
        if (vacancy.getSalary() != null)
            salaryVacancy.setText(presenter.getSalaryString(vacancy.getSalary()));
        cityVacancy.setText(vacancy.getArea().getName());
        experienceVacancy.setText(vacancy.getExperience().getName());
        scheduleVacancy.setText(vacancy.getSchedule().getName());
        companyVacancy.setText(vacancy.getEmployer().getName());
        if (vacancy.getAddress() != null && vacancy.getAddress().getStreet() != null)
            addressVacancy.setText(presenter.getAddressString(vacancy.getAddress()));
        if (vacancy.getAddress() != null &&
                vacancy.getAddress().getMetro() != null && vacancy.getAddress().getMetro().getStationName() != null) {
            subwayVacancy.setVisibility(View.VISIBLE);
            subwayVacancy.setText(vacancy.getAddress().getMetro().getStationName());
        }
        if (vacancy.getDescription() != null) {
            descriptionVacancy.setText(new CommonMethod().fromHtml(vacancy.getDescription()));
        }

        if (vacancy.getKeySkills() != null) {
            Log.d("TAG", vacancy.getKeySkills().toString());
        }

        allVacancyView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null)
            presenter.onStop();
    }
}
