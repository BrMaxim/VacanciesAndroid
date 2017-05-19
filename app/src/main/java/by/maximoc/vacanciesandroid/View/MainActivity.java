package by.maximoc.vacanciesandroid.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.Gson.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Presenter.VacanciesPresenter;
import by.maximoc.vacanciesandroid.Presenter.VacanciesPresenterImpl;
import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.RxScrolling;
import by.maximoc.vacanciesandroid.Adapter.VacanciesAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends MvpActivity<MainActivityView, VacanciesPresenter> implements MainActivityView {

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
    public VacanciesPresenter createPresenter() {
        return new VacanciesPresenterImpl(this);
    }

    @Override
    public void addDataToAdapter(Vacancies vacancies) {
        adapter.updateAdapter(vacancies);
        adapterClickListener();
    }

    private void scrollListener() {
        new RxScrolling().getScrollObservable(recyclerView, 0, layoutManager)
                .distinctUntilChanged()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(Integer page) {
                        getPresenter().getVacancies(String.valueOf(page));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
