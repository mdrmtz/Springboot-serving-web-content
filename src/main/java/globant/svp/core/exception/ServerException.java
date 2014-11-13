package globant.svp.core.exception;

public class ServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Internal Server Error";

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException() {
		super(defaultMessage);
	}
}
