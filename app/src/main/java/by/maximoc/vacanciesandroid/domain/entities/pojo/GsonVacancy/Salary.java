package by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salary {

    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("currency")
    @Expose
    private String currency;

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}