package by.maximoc.vacanciesandroid.ui.VacanciesList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.adapters.VacanciesAdapter;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.ui.DetailVacancy.view.VacancyDetailActivity;
import by.maximoc.vacanciesandroid.ui.VacanciesList.presenter.IVacanciesPresenter;
import by.maximoc.vacanciesandroid.ui.VacanciesList.presenter.VacanciesPresenter;
import by.maximoc.vacanciesandroid.utils.Constants;
import by.maximoc.vacanciesandroid.utils.RxScrolling;
import io.reactivex.disposables.CompositeDisposable;

public class VacanciesActivity extends MvpActivity<IVacanciesView, IVacanciesPresenter> implements IVacanciesView {

    private RecyclerView recyclerView;
    private VacanciesAdapter adapter;
    private LinearLayoutManager layoutManager;
    private CompositeDisposable composite = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VacanciesAdapter(null);
        recyclerView.setAdapter(adapter);
        scrollListener();
    }

    @NonNull
    @Override
    public IVacanciesPresenter createPresenter() {
        return new VacanciesPresenter(this);
    }

    @Override
    public void addDataToAdapter(Vacancies vacancies) {
        adapter.updateAdapter(vacancies);
        adapterClickListener();
    }

    private void scrollListener() {
        RxScrolling.getScrollObservable(recyclerView, 0, layoutManager)
                .distinctUntilChanged()
                .subscribe(page -> getPresenter().getVacancies(String.valueOf(page)));
    }


    private void adapterClickListener() {
        adapter.setClickListener(urlVacancy -> {
            presenter.itemClick(urlVacancy);
        });
    }

    @Override
    public void startDetailActivity(String urlVacancy) {
        Intent intent = new Intent(this, VacancyDetailActivity.class);
        intent.putExtra(Constants.URL_VACANCY, urlVacancy);
        startActivity(intent);
    }

    @Override
    public void showError() {
        if (!presenter.isAccessToInternet())
            Toast.makeText(this, "Нет доступа к сети", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        composite.dispose();
        if (getPresenter() != null) {
            getPresenter().onDestroy(adapter.getVacancies());
        }
    }
}
