package itec.fcc.rpp.event.exception;

import javax.ws.rs.core.Response.Status;

public abstract class EventException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ExceptionType exceptionType; 
	
	private int errorCode;
	
	private String errorMessage;

	public EventException(ExceptionType exceptionType, Status errorStatus, String message) {
		super("Exception=" + exceptionType.getValue() +" : ErrorCode=" + errorStatus.getStatusCode() + " : ErrorMessage=" + message);
		this.errorCode=errorStatus.getStatusCode();
		this.errorMessage=message;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
