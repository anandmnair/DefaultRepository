package com.anand.ws.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstname;
	
	private String lastname;
	
	public Person(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Person() {
	}

	@XmlElement
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@XmlElement
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
}
