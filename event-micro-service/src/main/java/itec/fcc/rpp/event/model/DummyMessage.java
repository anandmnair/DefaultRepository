package itec.fcc.rpp.event.model;

import java.util.Date;

public class DummyMessage {

	private String message;
	
	private Date date;
	
	public DummyMessage() {
	}
	
	public DummyMessage(String message, Date date) {
		super();
		this.message = message;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DummyMessage [message=" + message + ", date=" + date + "]";
	}

}
