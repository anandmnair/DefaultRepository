package itec.fcc.rpp.event.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EventExceptionMapper implements ExceptionMapper<EventException>{

	@Override
	public Response toResponse(EventException exception) {
		return Response.serverError().entity("Error = " + exception.getErrorCode() + " :: Message = " + exception.getMessage()).type(MediaType.APPLICATION_JSON).build();
	}

}
