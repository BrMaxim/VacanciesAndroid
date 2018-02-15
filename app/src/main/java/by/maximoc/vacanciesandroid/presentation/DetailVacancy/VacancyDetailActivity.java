package by.maximoc.vacanciesandroid.presentation.DetailVacancy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.presentation.Base.BaseActivity;
import by.maximoc.vacanciesandroid.utils.Application;
import by.maximoc.vacanciesandroid.utils.FlowLayout;
import by.maximoc.vacanciesandroid.utils.StringHtmlFormat;

public class VacancyDetailActivity extends BaseActivity<IVacancyDetailView, VacancyDetailPresenter>
        implements IVacancyDetailView {

    private static final String URL_VACANCY = "URL_VACANCY";
    private static final String NAME_VACANCY = "NAME_VACANCY";
    private TextView nameVacancy;
    private TextView salaryVacancy;
    private TextView cityVacancy;
    private TextView experienceVacancy;
    private TextView scheduleVacancy;
    private TextView companyVacancy;
    private TextView addressVacancy;
    private TextView subwayVacancy;
    private TextView descriptionVacancy;
    private LinearLayout allVacancyView;

    public static Intent getVacancyIntent(Context context, String urlVacancy, String nameVacancy) {
        Intent intent = new Intent(context, VacancyDetailActivity.class);
        intent.putExtra(URL_VACANCY, urlVacancy);
        intent.putExtra(NAME_VACANCY, nameVacancy);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_detail);

        nameVacancy = findViewById(R.id.name_vacancy);
        salaryVacancy = findViewById(R.id.salary_vacancy);
        cityVacancy = findViewById(R.id.city_vacancy);
        experienceVacancy = findViewById(R.id.experience_vacancy);
        scheduleVacancy = findViewById(R.id.schedule_vacancy);
        companyVacancy = findViewById(R.id.company_vacancy);
        addressVacancy = findViewById(R.id.address_vacancy);
        subwayVacancy = findViewById(R.id.subway_vacancy);
        descriptionVacancy = findViewById(R.id.descriptions_vacancy);
        allVacancyView = findViewById(R.id.all_vacancy_view);

        setTitle(getIntent().getStringExtra(NAME_VACANCY));
        String idVacancy = getIntent().getStringExtra(URL_VACANCY);
        presenter.getDetailInfo(idVacancy);
    }

    @Override
    public void showDetail(Vacancy vacancy) {

        nameVacancy.setText(vacancy.getName());
        cityVacancy.setText(vacancy.getArea().getName());
        experienceVacancy.setText(vacancy.getExperience().getName());
        scheduleVacancy.setText(vacancy.getSchedule().getName());
        companyVacancy.setText(vacancy.getEmployer().getName());
        salaryVacancy.setText(vacancy.getFormatSalary());
        addressVacancy.setText(vacancy.getFormatAddress());

        if (vacancy.getAddress() != null && vacancy.getAddress().getMetro() != null &&
                vacancy.getAddress().getMetro().getStationName() != null) {
            subwayVacancy.setVisibility(View.VISIBLE);
            subwayVacancy.setText(vacancy.getAddress().getMetro().getStationName());
        }
        if (vacancy.getDescription() != null) {
            descriptionVacancy.setText(StringHtmlFormat.fromHtml(vacancy.getDescription().trim()));
        }

        allVacancyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void addSkill(Vacancy vacancy) {
        TextView keySkill = findViewById(R.id.key_skill);
        FlowLayout flowLayout = findViewById(R.id.flow_layout);
        keySkill.setVisibility(View.VISIBLE);

        for (Object skill : vacancy.getKeySkills()) {
            TextView textViewSkill = (TextView) getLayoutInflater().
                    inflate(R.layout.incl_skill, null, false);
            textViewSkill.setText(skill.toString().replaceAll("\\{name=|\\}", ""));
            flowLayout.addView(textViewSkill);
        }
        flowLayout.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public VacancyDetailPresenter createPresenter() {
        VacancyDetailPresenter presenter = new VacancyDetailPresenter();
        ((Application) getApplication()).appComponent.inject(presenter);
        return presenter;
    }

}
