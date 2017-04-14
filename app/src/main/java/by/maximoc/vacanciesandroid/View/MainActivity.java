package by.maximoc.vacanciesandroid.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Presenter.VacanciesPresenter;
import by.maximoc.vacanciesandroid.Presenter.VacanciesPresenterImpl;
import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.VacanciesAdapter;

public class MainActivity extends MvpActivity<MainActivityView, VacanciesPresenter> implements MainActivityView {

    RecyclerView recyclerView;
    VacanciesAdapter adapter;
    LinearLayoutManager layoutManager;
    int stopScrollListener = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        presenter.getVacancies("0");
    }

    @NonNull
    @Override
    public VacanciesPresenter createPresenter() {
        return new  VacanciesPresenterImpl(this);
    }

    @Override
    public void showVacancies(Vacancies vacancies) {
        adapter = new VacanciesAdapter(vacancies);
        recyclerView.setAdapter(adapter);
        adapterClickListener();
        scrollListener();
    }

    @Override
    public void addDataToAdapter(Vacancies vacancies) {
        adapter.updateAdapter(vacancies);
    }

    private void scrollListener(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) //check for scroll down
                {
                    if(penultimateItemInAdapter()){
                        Log.d("TAG", "Count " + adapter.getItemCount());
                        stopScrollListener = layoutManager.findLastCompletelyVisibleItemPosition();
                        getPresenter().getVacancies(String.valueOf(adapter.getItemCount() / Constants.COUNT_PER_PAGE));
                    }
                }
            }
        });
    }

    public boolean penultimateItemInAdapter(){
        return (stopScrollListener != layoutManager.findLastCompletelyVisibleItemPosition() &&
                layoutManager.findLastCompletelyVisibleItemPosition() + Constants.COUNT_PER_PAGE / 3 ==
                        adapter.getItemCount() - Constants.COUNT_PER_PAGE / 3);
    }

    private void adapterClickListener() {
        adapter.setClickListener(urlVacancy -> {
            Intent intent = new Intent(this, VacancyDetailActivity.class);
            intent.putExtra(Constants.URL_VACANCY, urlVacancy);
            startActivity(intent);
        });
    }

    @Override
    public void showError() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
