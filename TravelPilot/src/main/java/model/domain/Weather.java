package model.domain;

public class Weather {
	private int weatherId;
	private double temperature;
	private String condition;
	private String updateTime;
	private String location;
	
	public Weather() { }
	
	public Weather(int weatherId, double temperature, String condition, String updateTime, String location) {
        this.weatherId = weatherId;
        this.temperature = temperature;
        this.condition = condition;
        this.updateTime = updateTime;
        this.location = location;
    }
	
	public void update(Weather updatedWeather) {
	    this.temperature = updatedWeather.temperature;
	    this.condition = updatedWeather.condition;
	    this.updateTime = updatedWeather.updateTime;
	    this.location = updatedWeather.location;
	}

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) { 
        this.updateTime = updateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}