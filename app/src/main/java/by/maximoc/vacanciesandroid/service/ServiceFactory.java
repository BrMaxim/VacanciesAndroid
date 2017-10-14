package by.maximoc.vacanciesandroid.service;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private static Retrofit retrofit;
    public static HhApiInterface hhApiInterface = getHhApiInterface();
    private static final String URL = "https://api.hh.ru/";

    private static HhApiInterface getHhApiInterface() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        hhApiInterface = retrofit.create(HhApiInterface.class);
        return hhApiInterface;
    }
}
