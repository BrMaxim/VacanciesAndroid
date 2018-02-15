package by.maximoc.vacanciesandroid.utils;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import by.maximoc.vacanciesandroid.R;
import okhttp3.ResponseBody;

public class ErrorHandler {

    public static String handleError(Throwable throwable, Context context) {
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            return getErrorMessage(responseBody);
        } else if (throwable instanceof SocketTimeoutException) {
            return context.getString(R.string.error_timeout);
        } else if (throwable instanceof IOException) {
            return context.getString(R.string.error_network);
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
