package by.maximoc.vacanciesandroid.DataBase;

import io.realm.RealmObject;

public class Salary extends RealmObject {

    private String currency;

    private Integer from;

    private String to;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
