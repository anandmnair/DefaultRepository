package com.anand.batch.bean;

public class DummyItem {
	
	private int id;
	
	private String name;
	
	public DummyItem(int id, String name) {
		this.id=id;
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "DummyItem [id=" + id + ", name=" + name + "]";
	}
	
}
