package by.maximoc.vacanciesandroid.presentation.VacanciesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.presentation.Base.BaseActivity;
import by.maximoc.vacanciesandroid.utils.Application;
import by.maximoc.vacanciesandroid.utils.EndlessScrollListener;

public class VacanciesActivity extends BaseActivity<IVacanciesView, VacanciesPresenter> implements IVacanciesView {

    private RecyclerView recyclerView;
    private VacanciesAdapter adapter;
    private LinearLayoutManager layoutManager;
    private static final String FIRST_PAGE = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancies);

        initRecyclerView();
        presenter.getVacancies(FIRST_PAGE);

        adapterClickListener();
        scrollListener();
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VacanciesAdapter();
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public VacanciesPresenter createPresenter() {
        VacanciesPresenter presenter = new VacanciesPresenter();
        ((Application) getApplication()).appComponent.inject(presenter);
        return presenter;
    }

    @Override
    public void addDataToAdapter(Vacancies vacancies) {
        adapter.updateAdapter(vacancies);
    }

    private void scrollListener() {
        recyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getVacancies(String.valueOf(page));
            }
        });
    }


    private void adapterClickListener() {
        adapter.setClickListener((urlVacancy, nameVacancy) -> presenter.itemClick(urlVacancy, nameVacancy));
    }

    @Override
    public void startNewActivity(Intent intent) {
        startActivity(intent);
    }

}
