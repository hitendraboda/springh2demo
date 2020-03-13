package com.boda.springh2demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BookDto {
	@NotEmpty(message = "title should not be null or empty")
	@NotBlank(message = "title should not be null or empty")
	private String title;

	@NotEmpty(message = "author should not be null or empty")
	@NotBlank(message = "author should not be null or empty")
	private String author;

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

	@Override
	public String toString() {
		return "BookDto [title=" + title + ", author=" + author + "]";
	}

}
