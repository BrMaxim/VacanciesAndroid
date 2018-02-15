package by.maximoc.vacanciesandroid.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {

    private static final String EMPTY_DATE = "EMPTY_DATE";

    public static String createData(String dataOldFormat) {
        String data = EMPTY_DATE;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    Locale.getDefault()).parse(dataOldFormat);
            data = String.valueOf(DateUtils.getRelativeTimeSpanString(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}
