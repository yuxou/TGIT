package model.domain;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class Plan {
    private int planId;
    private String planTitle;
    private String country;
    private Date startDate;
    private Date endDate;
    private List<Flight> flightInfo;
    private List<Accommodation> accommodationInfo;
    private boolean isPublic;
    private User writer;

    private Optional<List<Expense>> expenses;
    private Optional<List<User>> companions;
    private Optional<List<Checklist>> checklist;
    private Optional<List<Place>> places;
    private Optional<List<Weather>> weather;

    public Plan() { }

    public Plan(int planId, String planTitle, String country, Date startDate, Date endDate, 
                List<Flight> flightInfo, List<Accommodation> accommodationInfo, boolean isPublic, User writer) {
        this.planId = planId;
        this.planTitle = planTitle;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.flightInfo = flightInfo;
        this.accommodationInfo = accommodationInfo;
        this.isPublic = isPublic;
        this.writer = writer;
        this.expenses = Optional.empty();
        this.companions = Optional.empty();
        this.checklist = Optional.empty();
        this.places = Optional.empty();
        this.weather = Optional.empty();
    }

    public Plan(int planId, String planTitle, String country, Date startDate, Date endDate, 
                List<Flight> flightInfo, List<Accommodation> accommodationInfo, boolean isPublic, User writer, 
                Optional<List<Expense>> expenses, Optional<List<User>> companions, 
                Optional<List<Checklist>> checklist, Optional<List<Place>> places, 
                Optional<List<Weather>> weather) {
        this(planId, planTitle, country, startDate, endDate, flightInfo, accommodationInfo, isPublic, writer);
        this.expenses = expenses;
        this.companions = companions;
        this.checklist = checklist;
        this.places = places;
        this.weather = weather;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작함
        this.startDate = calendar.getTime();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작함
        this.endDate = calendar.getTime();
    }

    public List<Flight> getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(List<Flight> flightInfo) {
        this.flightInfo = flightInfo;
    }

    public List<Accommodation> getAccommodationInfo() {
        return accommodationInfo;
    }

    public void setAccommodationInfo(List<Accommodation> accommodationInfo) {
        this.accommodationInfo = accommodationInfo;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Optional<List<Expense>> getExpenses() {
        return expenses;
    }

    public void setExpenses(Optional<List<Expense>> expenses) {
        this.expenses = expenses;
    }

    public Optional<List<User>> getCompanions() {
        return companions;
    }

    public void setCompanions(Optional<List<User>> companions) {
        this.companions = companions;
    }

    public Optional<List<Checklist>> getChecklist() {
        return checklist;
    }

    public void setChecklist(Optional<List<Checklist>> checklist) {
        this.checklist = checklist;
    }

    public Optional<List<Place>> getPlaces() {
        return places;
    }

    public void setPlaces(Optional<List<Place>> places) {
        this.places = places;
    }

    public Optional<List<Weather>> getWeather() {
        return weather;
    }

    public void setWeather(Optional<List<Weather>> weather) {
        this.weather = weather;
    }
}