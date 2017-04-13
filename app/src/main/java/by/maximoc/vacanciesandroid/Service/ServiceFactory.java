package by.maximoc.vacanciesandroid.Service;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private Retrofit retrofit;
    private static HhApiInterface hhApiInterface;

    public HhApiInterface getHhApiInterface(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hh.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        hhApiInterface = retrofit.create(HhApiInterface.class);
        return hhApiInterface;
    }
}
