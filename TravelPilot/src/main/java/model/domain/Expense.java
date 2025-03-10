package model.domain;

import java.util.Calendar;
import java.util.Date;

public class Expense {
    private int expenseId;
    private String place;
    private Date expenseDate;
    private String category;
    private double cost;
    private String notes; // Optional

    public Expense() { }

    public Expense(int expenseId, String place, int expenseYear, int expenseMonth, int expenseDay, String category, double cost) {
        this.expenseId = expenseId;
        this.place = place;
        this.expenseDate = createDate(expenseYear, expenseMonth, expenseDay);
        this.category = category;
        this.cost = cost;
    }

    public Expense(int expenseId, String place, int expenseYear, int expenseMonth, int expenseDay, String category, double cost, 
            String notes) {
        this(expenseId, place, expenseYear, expenseMonth, expenseDay, category, cost);
        this.notes = notes;
    }

    public Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작함
        return new Date(calendar.getTimeInMillis());
    }
    
    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public java.sql.Date getSqlExpenseDate() {
        return new java.sql.Date(expenseDate.getTime());
    }

    public void setExpenseDate(int year, int month, int day) {
        this.expenseDate = createDate(year, month, day);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}