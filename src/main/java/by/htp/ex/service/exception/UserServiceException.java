package by.htp.ex.service.exception;

public class UserServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);
	}
	
	public UserServiceException(Exception exception) {
		super(exception);
	}
	
	public UserServiceException(String message, Exception exception) {
		super(message, exception);
	}
}
