package by.maximoc.vacanciesandroid.data.repositories.vacancies;

import android.content.Context;

import by.maximoc.vacanciesandroid.data.dataBase.IVacanciesDataBase;
import by.maximoc.vacanciesandroid.data.repositories.IVacanciesDataRepository;
import by.maximoc.vacanciesandroid.utils.NetworkState;

public class VacanciesDataStoreFactory {

    private final IVacanciesDataBase iVacanciesDataBase;
    private Context context;

    public VacanciesDataStoreFactory(IVacanciesDataBase iVacanciesDataBase, Context context) {
        this.iVacanciesDataBase = iVacanciesDataBase;
        this.context = context;
    }

    public IVacanciesDataRepository create(){
        if(!NetworkState.isAccessToInternet(context) && iVacanciesDataBase.isCached()){
            return new VacanciesLocalStorage(iVacanciesDataBase);
        }else{
            return new VacanciesCloudStorage(iVacanciesDataBase);
        }
    }
}
