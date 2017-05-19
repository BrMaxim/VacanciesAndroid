package by.maximoc.vacanciesandroid.View;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.CommonMethod;
import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.Gson.GsonVacancy.Vacancy;
import by.maximoc.vacanciesandroid.Presenter.VacancyDetailPresenter;
import by.maximoc.vacanciesandroid.Presenter.VacancyDetailPresenterImpl;
import by.maximoc.vacanciesandroid.R;
import ru.suvitruf.flowlayoutexample.view.FlowLayout;

public class VacancyDetailActivity extends MvpActivity<VacancyDetailView, VacancyDetailPresenter> implements VacancyDetailView {

    private TextView nameVacancy, salaryVacancy, cityVacancy, experienceVacancy, scheduleVacancy,
            companyVacancy, addressVacancy, subwayVacancy, descriptionVacancy;
    private FlowLayout flowLayout;
    private TextView keySkill;
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

        if (vacancy.getKeySkills().size() != 0) {
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

        allVacancyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo == null)
            Toast.makeText(this, "Нет доступа к сети", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null)
            presenter.onStop();
    }
}
