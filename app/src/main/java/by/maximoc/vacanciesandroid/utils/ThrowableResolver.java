package by.maximoc.vacanciesandroid.utils;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import by.maximoc.vacanciesandroid.R;
import okhttp3.ResponseBody;

public class ThrowableResolver {

    public static String handleError(Throwable throwable, Context context) {
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            return getErrorMessage(responseBody);
        } else if (throwable instanceof SocketTimeoutException) {
            return context.getString(R.string.error_handling_timeout);
        } else if (throwable instanceof IOException) {
            return context.getString(R.string.error_handling_network_error);
        } else {
            return throwable.getLocalizedMessage();
        }
    }

    private static String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
