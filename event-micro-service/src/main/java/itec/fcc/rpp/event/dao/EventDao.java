package itec.fcc.rpp.event.dao;

import java.util.List;

import itec.fcc.rpp.event.model.Event;

public interface EventDao {
	
	Event addEvent(Event event);
	
	Event findEvent(Long eventId);
	
	List<Event> findAllEvents();
}
