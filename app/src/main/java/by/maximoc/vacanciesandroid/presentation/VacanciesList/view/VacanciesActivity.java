package by.maximoc.vacanciesandroid.presentation.VacanciesList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.adapters.VacanciesAdapter;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.presentation.VacanciesList.presenter.IVacanciesPresenter;
import by.maximoc.vacanciesandroid.presentation.VacanciesList.presenter.VacanciesPresenter;
import by.maximoc.vacanciesandroid.utils.EndlessScrollListener;

public class VacanciesActivity extends MvpActivity<IVacanciesView, IVacanciesPresenter> implements IVacanciesView {

    private RecyclerView recyclerView;
    private VacanciesAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ProgressBar progress;
    private EndlessScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progress = (ProgressBar) findViewById(R.id.progress_bar);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VacanciesAdapter();
        recyclerView.setAdapter(adapter);

        presenter.getVacancies("0");

        adapterClickListener();
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
    }

    @Override
    public void showError(String throwable) {
        Toast.makeText(this, R.string.vacancies_wrong, Toast.LENGTH_SHORT).show();
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
        adapter.setClickListener(urlVacancy -> {
            presenter.itemClick(urlVacancy);
        });
    }

    @Override
    public void startDetailActivity(Intent intent) {
        startActivity(intent);
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
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
