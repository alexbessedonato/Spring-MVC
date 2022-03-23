package com.example;

public class Greeting {
	private final long id;
	private final String content;
	private final String language;

	public Greeting(long id, String content, String language) {
		this.id = id;
		this.content = content;
		this.language = language;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getLanguage() {
		return language;
	}
}
