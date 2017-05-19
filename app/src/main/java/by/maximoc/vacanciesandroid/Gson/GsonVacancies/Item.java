package by.maximoc.vacanciesandroid.Gson.GsonVacancies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("salary")
    @Expose
    private Salary salary;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("relations")
    @Expose
    private List<Object> relations = null;
    @SerializedName("employer")
    @Expose
    private Employer employer;
    @SerializedName("response_letter_required")
    @Expose
    private Boolean responseLetterRequired;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("alternate_url")
    @Expose
    private String alternateUrl;
    @SerializedName("apply_alternate_url")
    @Expose
    private String applyAlternateUrl;
    @SerializedName("department")
    @Expose
    private Department department;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("snippet")
    @Expose
    private Snippet snippet;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<Object> getRelations() {
        return relations;
    }

    public void setRelations(List<Object> relations) {
        this.relations = relations;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Boolean getResponseLetterRequired() {
        return responseLetterRequired;
    }

    public void setResponseLetterRequired(Boolean responseLetterRequired) {
        this.responseLetterRequired = responseLetterRequired;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAlternateUrl() {
        return alternateUrl;
    }

    public void setAlternateUrl(String alternateUrl) {
        this.alternateUrl = alternateUrl;
    }

    public String getApplyAlternateUrl() {
        return applyAlternateUrl;
    }

    public void setApplyAlternateUrl(String applyAlternateUrl) {
        this.applyAlternateUrl = applyAlternateUrl;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

}