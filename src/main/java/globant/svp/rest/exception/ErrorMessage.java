package globant.svp.rest.exception;

public class ErrorMessage {

	public static final String MESSAGE_TYPE_FIELD = "field-type";
	public static final String MESSAGE_TYPE_OTHER = "other-type";
	private String type;
	private String attributeName;
	private String message;
	private String severity;

	public ErrorMessage(String type, String attributeName, String message,
			String severity) {
		super();
		this.type = type;
		this.attributeName = attributeName;
		this.message = message;
		this.severity = severity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getMessage() {
		return message;
	}
}
