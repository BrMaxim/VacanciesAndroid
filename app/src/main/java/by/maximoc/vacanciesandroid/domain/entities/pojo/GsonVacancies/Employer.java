package by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import by.maximoc.vacanciesandroid.domain.entities.pojo.LogoUrls;
import io.realm.RealmObject;

public class Employer extends RealmObject {

    @SerializedName("logo_urls")
    @Expose
    private LogoUrls logoUrls;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("alternate_url")
    @Expose
    private String alternateUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("trusted")
    @Expose
    private boolean trusted;

    public LogoUrls getLogoUrls() {
        return logoUrls;
    }

    public void setLogoUrls(LogoUrls logoUrls) {
        this.logoUrls = logoUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlternateUrl() {
        return alternateUrl;
    }

    public void setAlternateUrl(String alternateUrl) {
        this.alternateUrl = alternateUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getTrusted() {
        return trusted;
    }

    public void setTrusted(Boolean trusted) {
        this.trusted = trusted;
    }

}