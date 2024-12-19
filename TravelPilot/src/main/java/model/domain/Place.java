package model.domain;

public class Place {	
	private int placeId;
	private String name;
	private String location;
	private String memo; // Optional
	
	public Place() { }
		
	public Place(int placeId, String name, String location) {
		this.placeId = placeId;
		this.name = name;
		this.location = location;
	}
	
	public Place(int placeId, String name, String location, String memo) {
		this(placeId, name, location);
		this.memo = memo;
	}
	
	public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}