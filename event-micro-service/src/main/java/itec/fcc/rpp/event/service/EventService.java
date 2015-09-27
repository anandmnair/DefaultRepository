package itec.fcc.rpp.event.service;

import java.util.List;

import itec.fcc.rpp.event.model.Event;

public interface EventService {

	Event addEvent(Event event);
	
	Event getEvent(Long eventId);
	
	List<Event> getEvents();
}
