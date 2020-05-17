package hr.fer.zemris.java.hw16.jvdraw;

/**
 * Exception that is used {@link JVDraw}.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class JVDrawException extends RuntimeException {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates new {@link JVDrawException} object.
	 * 
	 * @param message message about error that occurred
	 */
	public JVDrawException(String message) {
		super(message);
	}

}
