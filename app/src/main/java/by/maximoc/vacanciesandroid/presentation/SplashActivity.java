package by.maximoc.vacanciesandroid.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.maximoc.vacanciesandroid.presentation.VacanciesList.view.VacanciesActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, VacanciesActivity.class);
        startActivity(intent);
        finish();
    }
}
