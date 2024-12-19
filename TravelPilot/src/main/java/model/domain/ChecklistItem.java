package model.domain;

public class ChecklistItem {
	private int itemId;
	private String name;
	private boolean isCompleted;
	
	public ChecklistItem() { }
	
	public ChecklistItem(int itemId, String name, boolean isCompleted) {
		this.itemId = itemId;
		this.name = name;
		this.isCompleted = isCompleted;
	}
	
	public int getItemId() {
	    return itemId;
	}

	public void setItemId(int itemId) {
	    this.itemId = itemId;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public boolean isCompleted() {
	    return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
	    this.isCompleted = isCompleted;
	}
}