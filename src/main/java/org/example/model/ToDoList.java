package org.example.model;

import lombok.Data;

@Data
public class ToDoList {

    private String title;
    private String description;
    private Boolean done;

    public ToDoList(String title, String description, Boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}
	
	public void setDone(Boolean done) {
		this.done = done;
	}

}
