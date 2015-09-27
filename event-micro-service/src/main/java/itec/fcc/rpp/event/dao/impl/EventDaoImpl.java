package itec.fcc.rpp.event.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import itec.fcc.rpp.event.dao.EventDao;
import itec.fcc.rpp.event.model.Event;

@Repository
public class EventDaoImpl implements EventDao {

	private static List<Event> events = new ArrayList<Event>();
	
	@Override
	public Event addEvent(Event event) {
		event.setEventId(events.size()+1L);
		events.add(event);
		return event;
	}

	@Override
	public Event findEvent(Long eventId) {
		return events.get(eventId.intValue()-1);
	}

	@Override
	public List<Event> findAllEvents() {
		return events;
	}

}
