package model.domain;

import java.util.List;

public class Checklist {
	private int checklistId;
	private String category;
	private List<ChecklistItem> items;
	
	public Checklist() { }
	
	public Checklist(int checklistId, String category, List<ChecklistItem> items) {
		this.checklistId = checklistId;
		this.category = category;
		this.items = items;
	}
	
	public int getChecklistId() {
	    return checklistId;
	}

	public void setChecklistId(int checklistId) {
	    this.checklistId = checklistId;
	}

	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
	}

	public List<ChecklistItem> getItems() {
	    return items;
	}

	public void setItems(List<ChecklistItem> items) {
	    this.items = items;
	}
}