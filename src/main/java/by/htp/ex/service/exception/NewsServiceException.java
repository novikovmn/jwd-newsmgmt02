package by.htp.ex.service.exception;

public class NewsServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NewsServiceException() {
		super();
	}

	public NewsServiceException(String message) {
		super(message);
	}
	
	public NewsServiceException(Exception exception) {
		super(exception);
	}
	
	public NewsServiceException(String message, Exception exception) {
		super(message, exception);
	}
}
