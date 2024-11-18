package model.domain;

import java.util.Calendar;
import java.util.Date;

public class Accommodation {
    private int accommodationId;  
    private String name;         
    private Date checkInDate;    
    private Date checkOutDate;   
    private double cost;         

    public Accommodation(int accommodationId, String name,
                         int checkInYear, int checkInMonth, int checkInDay,
                         int checkOutYear, int checkOutMonth, int checkOutDay,
                         double cost) {
        this.accommodationId = accommodationId;
        this.name = name;
        this.checkInDate = createDate(checkInYear, checkInMonth, checkInDay);
        this.checkOutDate = createDate(checkOutYear, checkOutMonth, checkOutDay);
        this.cost = cost;
    }

    public void update(Accommodation updatedAccommodation) {
        this.name = updatedAccommodation.name;
        this.checkInDate = updatedAccommodation.checkInDate;
        this.checkOutDate = updatedAccommodation.checkOutDate;
        this.cost = updatedAccommodation.cost;
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작함
        return calendar.getTime();
    }
    
    public int getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(int accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(int year, int month, int day) {
        this.checkInDate = createDate(year, month, day);
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(int year, int month, int day) {
        this.checkOutDate = createDate(year, month, day);
    }
    
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}