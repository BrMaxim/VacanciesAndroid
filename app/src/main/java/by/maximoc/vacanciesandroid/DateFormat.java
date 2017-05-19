package by.maximoc.vacanciesandroid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {

    public static String createData(String dataOldFormat) {
        String data;
        String now = new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(new Date());
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("dd MMMM", Locale.getDefault());
        try {
            String dataNewFormat = df2.format(df.parse(dataOldFormat));
            if (now.equals(dataNewFormat))
                data = "Сегодня";
            else {
                data = dataNewFormat;

                if (data.substring(0, 1).equals("0")) {
                    data = data.replaceFirst("0", "");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            data = "";
        }
        return data;
    }
}
