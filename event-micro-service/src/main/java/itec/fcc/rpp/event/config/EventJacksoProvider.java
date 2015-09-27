package itec.fcc.rpp.event.config;

import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventJacksoProvider extends JacksonJsonProvider {

	private static final String EVENT_DATE_FORMAT = "dd-MMM-yyyy HH:mm:ss z";
	
	private String eventDateFormat = EVENT_DATE_FORMAT;
	
	@Override
	public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
		ObjectMapper objectMapper = super.locateMapper(type, mediaType);
		SimpleDateFormat df = new SimpleDateFormat(eventDateFormat);
		objectMapper.setDateFormat(df);
		return objectMapper;
	}
}
