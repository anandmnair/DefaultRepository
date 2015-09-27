package itec.fcc.rpp.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itec.fcc.rpp.event.dao.EventDao;
import itec.fcc.rpp.event.model.Event;
import itec.fcc.rpp.event.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;
	
	@Override
	public Event addEvent(Event event) {
		return eventDao.addEvent(event);
	}

	@Override
	public Event getEvent(Long eventId) {
		return eventDao.findEvent(eventId);
	}

	@Override
	public List<Event> getEvents() {
		return eventDao.findAllEvents();
	}

}
