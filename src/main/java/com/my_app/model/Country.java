package com.my_app.model;

public class Country {
	private Long id;
	private String name;

	public Country() {
	}

	public Country(String name) {
		super();
		this.name = name;
	}

	public Country(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Country [id=%s, name=%s]", id, name);
	}

}
