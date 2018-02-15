package by.maximoc.vacanciesandroid.presentation.Base;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;


public class BasePresenter<T extends IBaseView> extends MvpNullObjectBasePresenter<T> {

    private final CompositeDisposable composite;
    @Inject
    protected Context context;

    protected BasePresenter() {
        composite = new CompositeDisposable();
    }

    protected <O>Single<O> wrapObservable(Single<O> single) {
        return single
                .doOnSubscribe(disposable -> {
                    composite.add(disposable);
                    getView().showProgress();
                })
                .doAfterTerminate(() -> getView().hideProgress());
    }

    @Override
    public void detachView() {
        super.detachView();
        composite.clear();
    }
}
