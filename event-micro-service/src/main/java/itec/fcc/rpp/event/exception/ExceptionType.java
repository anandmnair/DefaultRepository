package itec.fcc.rpp.event.exception;

public enum ExceptionType {

	TECHNICAL_EXCEPTION("Technical Exception"), FUNCTIONAL_EXCEPTION("Functional Exception");
	
	private String value;
	
	private ExceptionType(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
