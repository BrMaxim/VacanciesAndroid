package by.maximoc.vacanciesandroid.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.maximoc.vacanciesandroid.presentation.VacanciesList.VacanciesActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, VacanciesActivity.class));
        finish();
    }
}
