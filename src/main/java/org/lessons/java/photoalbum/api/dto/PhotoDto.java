package org.lessons.java.photoalbum.api.dto;

public class PhotoDto {
	private int id;
	private String title;
	private String description;
	private String url;
	private boolean visible;
	
	public PhotoDto() {}
	public PhotoDto(String title) {
		setTitle(title);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] Photo:\n"
					+ "title: " + getTitle() + "\n"
					+ "description: " + getDescription() + "\n"
					+ "url: " + getUrl() + "\n"
					+ "visibility: " + isVisible();
	}
	
}
