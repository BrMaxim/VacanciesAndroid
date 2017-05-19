package by.maximoc.vacanciesandroid.Gson.GsonVacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vacancy {

    @SerializedName("alternate_url")
    @Expose
    private String alternateUrl;
    @SerializedName("code")
    @Expose
    private Object code;
    @SerializedName("premium")
    @Expose
    private Boolean premium;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;
    @SerializedName("suitable_resumes_url")
    @Expose
    private Object suitableResumesUrl;
    @SerializedName("site")
    @Expose
    private Site site;
    @SerializedName("billing_type")
    @Expose
    private BillingType billingType;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("test")
    @Expose
    private Object test;
    @SerializedName("accept_handicapped")
    @Expose
    private Boolean acceptHandicapped;
    @SerializedName("experience")
    @Expose
    private Experience experience;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("key_skills")
    @Expose
    private List<Object> keySkills = null;
    @SerializedName("allow_messages")
    @Expose
    private Boolean allowMessages;
    @SerializedName("employment")
    @Expose
    private Employment employment;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("response_url")
    @Expose
    private Object responseUrl;
    @SerializedName("salary")
    @Expose
    private Salary salary;
    @SerializedName("archived")
    @Expose
    private Boolean archived;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contacts")
    @Expose
    private Object contacts;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("relations")
    @Expose
    private List<Object> relations = null;
    @SerializedName("employer")
    @Expose
    private Employer employer;
    @SerializedName("response_letter_required")
    @Expose
    private Boolean responseLetterRequired;
    @SerializedName("apply_alternate_url")
    @Expose
    private String applyAlternateUrl;
    @SerializedName("quick_responses_allowed")
    @Expose
    private Boolean quickResponsesAllowed;
    @SerializedName("negotiations_url")
    @Expose
    private Object negotiationsUrl;
    @SerializedName("department")
    @Expose
    private Object department;
    @SerializedName("branded_description")
    @Expose
    private Object brandedDescription;
    @SerializedName("hidden")
    @Expose
    private Boolean hidden;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("specializations")
    @Expose
    private List<Specialization> specializations = null;

    public String getAlternateUrl() {
        return alternateUrl;
    }

    public void setAlternateUrl(String alternateUrl) {
        this.alternateUrl = alternateUrl;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Object getSuitableResumesUrl() {
        return suitableResumesUrl;
    }

    public void setSuitableResumesUrl(Object suitableResumesUrl) {
        this.suitableResumesUrl = suitableResumesUrl;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public BillingType getBillingType() {
        return billingType;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Object getTest() {
        return test;
    }

    public void setTest(Object test) {
        this.test = test;
    }

    public Boolean getAcceptHandicapped() {
        return acceptHandicapped;
    }

    public void setAcceptHandicapped(Boolean acceptHandicapped) {
        this.acceptHandicapped = acceptHandicapped;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Object> getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(List<Object> keySkills) {
        this.keySkills = keySkills;
    }

    public Boolean getAllowMessages() {
        return allowMessages;
    }

    public void setAllowMessages(Boolean allowMessages) {
        this.allowMessages = allowMessages;
    }

    public Employment getEmployment() {
        return employment;
    }

    public void setEmployment(Employment employment) {
        this.employment = employment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(Object responseUrl) {
        this.responseUrl = responseUrl;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getContacts() {
        return contacts;
    }

    public void setContacts(Object contacts) {
        this.contacts = contacts;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public String getApplyAlternateUrl() {
        return applyAlternateUrl;
    }

    public void setApplyAlternateUrl(String applyAlternateUrl) {
        this.applyAlternateUrl = applyAlternateUrl;
    }

    public Boolean getQuickResponsesAllowed() {
        return quickResponsesAllowed;
    }

    public void setQuickResponsesAllowed(Boolean quickResponsesAllowed) {
        this.quickResponsesAllowed = quickResponsesAllowed;
    }

    public Object getNegotiationsUrl() {
        return negotiationsUrl;
    }

    public void setNegotiationsUrl(Object negotiationsUrl) {
        this.negotiationsUrl = negotiationsUrl;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public Object getBrandedDescription() {
        return brandedDescription;
    }

    public void setBrandedDescription(Object brandedDescription) {
        this.brandedDescription = brandedDescription;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }
}

