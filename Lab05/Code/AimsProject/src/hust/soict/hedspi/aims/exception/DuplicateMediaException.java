package hust.soict.hedspi.aims.exception;

public class DuplicateMediaException extends Exception {
	public DuplicateMediaException() {
        super();
    }
    public DuplicateMediaException(String message) {
        super(message);
    }
    public DuplicateMediaException(Throwable cause) {
        super(cause);
    }
    public DuplicateMediaException(String message, Throwable cause) {
        super(message, cause);
    }
}
