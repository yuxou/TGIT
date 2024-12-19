package model.domain;

import java.util.Calendar;
import java.util.Date;

public class Flight {
    private int flightId;
    private String departure;
    private String destination;
    private Date departureDate;
    private String departureTime;
    private Date arrivalDate;
    private String arrivalTime;
    private double cost;

    public Flight() { }

    public Flight(int flightId, String departure, String destination,
                  int departureYear, int departureMonth, int departureDay, String departureTime,
                  int arrivalYear, int arrivalMonth, int arrivalDay, String arrivalTime, double cost) {
        this.flightId = flightId;
        this.departure = departure;
        this.destination = destination;
        this.departureDate = createDate(departureYear, departureMonth, departureDay);
        this.departureTime = departureTime;
        this.arrivalDate = createDate(arrivalYear, arrivalMonth, arrivalDay);
        this.arrivalTime = arrivalTime;
        this.cost = cost;
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // 월은 0부터 시작 
        return calendar.getTime();
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(int year, int month, int day) {
        this.departureDate = createDate(year, month, day);
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(int year, int month, int day) {
        this.arrivalDate = createDate(year, month, day);
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void update(Flight updatedFlight) {
        this.departure = updatedFlight.departure;
        this.destination = updatedFlight.destination;
        this.departureDate = updatedFlight.departureDate;
        this.departureTime = updatedFlight.departureTime;
        this.arrivalDate = updatedFlight.arrivalDate;
        this.arrivalTime = updatedFlight.arrivalTime;
        this.cost = updatedFlight.cost;
    }
}