package itec.fcc.rpp.event.resource;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import itec.fcc.rpp.event.exception.FunctionalException;
import itec.fcc.rpp.event.exception.TechnicalException;
import itec.fcc.rpp.event.model.DummyMessage;
import itec.fcc.rpp.event.model.Event;
import itec.fcc.rpp.event.service.EventService;

@Component
@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

	@Autowired
	private EventService eventService;
	
	@GET
	//@Path("/")
	public Response getEvents() {
		return Response.ok().entity(eventService.getEvents()).build();
	}
	
	
	@GET
	@Path("/{eventId}")
	public Response getEvent(@PathParam("eventId") Long eventId) {
		return Response.ok().entity(eventService.getEvent(eventId)).build();
	}
	
	@POST
	@Path("/add")
	public Response addEvent(@Context UriInfo uriInfo, Event event) {
		event=eventService.addEvent(event);
		System.out.println(event);
		
		return Response
				.created(uriInfo.getRequestUri())
				.entity(event)
				.link(uriInfo.getBaseUriBuilder().path("event").path("{eventId}").build(event.getEventId()), "getEvent")
				.build();
	}
	
	@GET
	@Path("/info/{message}")
	public Response getInfoMessage(@PathParam("message") String message) {
		if("Err".equals(message)) {
			throw new RuntimeException(" : rt :error message");
		}
		if("TechErr".equals(message)) {
			throw new TechnicalException(Status.BAD_REQUEST, "tech error message");
		}
		if("FuncErr".equals(message)) {
			throw new FunctionalException(Status.BAD_REQUEST, "fun error message");
		}
		return Response.ok().entity(new DummyMessage(message, new Date())).build();
	}
	
	@POST
	@Path("/info/add")
	public Response addInfo(@Context UriInfo uriInfo, DummyMessage dummyMessage) {
		System.out.println(dummyMessage);
		return Response.created(uriInfo.getRequestUri()).entity(dummyMessage).build();
	}

}
