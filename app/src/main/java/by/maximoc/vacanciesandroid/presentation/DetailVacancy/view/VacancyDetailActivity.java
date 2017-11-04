package by.maximoc.vacanciesandroid.presentation.DetailVacancy.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.presenter.IVacancyDetailPresenter;
import by.maximoc.vacanciesandroid.presentation.DetailVacancy.presenter.VacancyDetailPresenter;
import by.maximoc.vacanciesandroid.utils.Constants;
import by.maximoc.vacanciesandroid.utils.FlowLayout;
import by.maximoc.vacanciesandroid.utils.StringHtmlFormat;

public class VacancyDetailActivity extends MvpActivity<IVacancyDetailView, IVacancyDetailPresenter>
        implements IVacancyDetailView {

    private TextView nameVacancy, salaryVacancy, cityVacancy, experienceVacancy, scheduleVacancy,
            companyVacancy, addressVacancy, subwayVacancy, descriptionVacancy;
    private FlowLayout flowLayout;
    private TextView keySkill;
    private String idVacancy;
    private LinearLayout allVacancyView;
    private ProgressBar progress;

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
        progress = (ProgressBar) findViewById(R.id.progress_bar);

        allVacancyView.setVisibility(View.GONE);

        idVacancy = getIntent().getStringExtra(Constants.URL_VACANCY);

        if (presenter != null) {
            presenter.getDetailInfo(idVacancy);
        }
    }

    @NonNull
    @Override
    public VacancyDetailPresenter createPresenter() {
        return new VacancyDetailPresenter(this);
    }

    @Override
    public void showDetail(Vacancy vacancy) {

        nameVacancy.setText(vacancy.getName());

        if (vacancy.getSalary() != null) {
            salaryVacancy.setText(presenter.getSalaryString(vacancy.getSalary()));
        }
        cityVacancy.setText(vacancy.getArea().getName());

        experienceVacancy.setText(vacancy.getExperience().getName());

        scheduleVacancy.setText(vacancy.getSchedule().getName());

        companyVacancy.setText(vacancy.getEmployer().getName());

        if (vacancy.getAddress() != null && vacancy.getAddress().getStreet() != null) {
            addressVacancy.setText(presenter.getAddressString(vacancy.getAddress()));
        }
        if (vacancy.getAddress() != null &&
                vacancy.getAddress().getMetro() != null && vacancy.getAddress().getMetro().getStationName() != null) {
            subwayVacancy.setVisibility(View.VISIBLE);
            subwayVacancy.setText(vacancy.getAddress().getMetro().getStationName());
        }
        if (vacancy.getDescription() != null) {
            descriptionVacancy.setText(new StringHtmlFormat().fromHtml(vacancy.getDescription()));
        }

        if (vacancy.getKeySkills().size() != 0) {
            addSkill(vacancy);
        }
        allVacancyView.setVisibility(View.VISIBLE);
    }

    private void addSkill(Vacancy vacancy) {
        keySkill = (TextView) findViewById(R.id.key_skill);
        keySkill.setVisibility(View.VISIBLE);
        flowLayout = (FlowLayout) findViewById(R.id.flow_layout);

        for (int i = 0; i < vacancy.getKeySkills().size(); i++) {
            final TextView skillText = new TextView(this);
            skillText.setTextSize(14);
            skillText.setPadding(5, 5, 5, 5);
            skillText.setTextColor(getResources().getColor(R.color.colorWhite));
            skillText.setBackgroundResource(R.drawable.rectangle_skills);
            skillText.setText(vacancy.getKeySkills().get(i).toString().replaceAll("\\{name=|\\}", ""));
            flowLayout.addView(skillText);
        }
        flowLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
