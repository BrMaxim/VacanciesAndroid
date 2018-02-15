package by.maximoc.vacanciesandroid.presentation.Base;

import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import by.maximoc.vacanciesandroid.R;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> implements IBaseView {

    @Override
    public void showProgress() {
        View view = findViewById(R.id.progress_bar);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        View view = findViewById(R.id.progress_bar);
        view.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
