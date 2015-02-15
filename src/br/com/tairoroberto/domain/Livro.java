package br.com.tairoroberto.domain;

import java.io.Serializable;

public class Livro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1315821447163156070L;
	
	private String title;
	private String author;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
