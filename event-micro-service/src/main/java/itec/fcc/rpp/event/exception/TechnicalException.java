package itec.fcc.rpp.event.exception;

import javax.ws.rs.core.Response.Status;

public class TechnicalException extends EventException {

	private static final long serialVersionUID = 1L;
	
	public TechnicalException(Status errorStatus, String message) {
		super(ExceptionType.TECHNICAL_EXCEPTION,errorStatus, message);
	}
	
}
