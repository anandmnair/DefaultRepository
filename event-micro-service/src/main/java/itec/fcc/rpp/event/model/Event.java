package itec.fcc.rpp.event.model;

import java.time.LocalDateTime;

public class Event {

	private Long eventId;
	
	private String eventName;
	
	private LocalDateTime dateTime =LocalDateTime.now();

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", dateTime=" + dateTime + "]";
	}
	
}
