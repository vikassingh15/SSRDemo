package com.demo.ssr.comments;

public class Comment {
	private Long id;
	private String author;
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Comment(String author, String content) {
		setAuthor(author);
		setContent(content);
	}
}
