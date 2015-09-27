package itec.fcc.rpp.event.exception;

import javax.ws.rs.core.Response.Status;

public class FunctionalException extends EventException {

	private static final long serialVersionUID = 1L;
	
	public FunctionalException(Status errorStatus, String message) {
		super(ExceptionType.FUNCTIONAL_EXCEPTION,errorStatus, message);
	}
	
}
