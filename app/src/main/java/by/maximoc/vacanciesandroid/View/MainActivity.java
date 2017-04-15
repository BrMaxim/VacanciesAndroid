package by.maximoc.vacanciesandroid.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import by.maximoc.vacanciesandroid.Constants;
import by.maximoc.vacanciesandroid.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.Presenter.VacanciesPresenter;
import by.maximoc.vacanciesandroid.Presenter.VacanciesPresenterImpl;
import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.VacanciesAdapter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends MvpActivity<MainActivityView, VacanciesPresenter> implements MainActivityView {

    RecyclerView recyclerView;
    VacanciesAdapter adapter;
    LinearLayoutManager layoutManager;

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
        return new  VacanciesPresenterImpl(this);
    }

    @Override
    public void addDataToAdapter(Vacancies vacancies) {
        adapter.updateAdapter(vacancies);
        adapterClickListener();
    }

    private void scrollListener(){
        getScrollObservable(recyclerView, 0, layoutManager)
                .distinctUntilChanged()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        getPresenter().getVacancies(String.valueOf(integer / Constants.COUNT_PER_PAGE));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static Observable<Integer> getScrollObservable(RecyclerView recyclerView,
                                                          int emptyListCount, LinearLayoutManager layoutManager) {
        return Observable.create(subscriber -> {
            final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (!subscriber.isDisposed()) {
                        int position = layoutManager.findLastCompletelyVisibleItemPosition();
                        int updatePosition = Constants.COUNT_PER_PAGE / 3;
                        if (position >= updatePosition) {
                            subscriber.onNext(recyclerView.getAdapter().getItemCount());
                        }
                    }
                }
            };
            recyclerView.addOnScrollListener(sl);
            if (recyclerView.getAdapter().getItemCount() == emptyListCount) {
                subscriber.onNext(recyclerView.getAdapter().getItemCount());
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

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
