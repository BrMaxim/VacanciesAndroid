package by.maximoc.vacanciesandroid.Gson.GsonStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("responses")
    @Expose
    private Object responses;
    @SerializedName("views")
    @Expose
    private Object views;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getResponses() {
        return responses;
    }

    public void setResponses(Object responses) {
        this.responses = responses;
    }

    public Object getViews() {
        return views;
    }

    public void setViews(Object views) {
        this.views = views;
    }

}
