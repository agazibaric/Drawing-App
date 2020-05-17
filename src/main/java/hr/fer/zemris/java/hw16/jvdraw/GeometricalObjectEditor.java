package hr.fer.zemris.java.hw16.jvdraw;

import javax.swing.JPanel;

/**
 * Class represents general form of panel that is showed
 * to user when it wants to change geometrical object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public abstract class GeometricalObjectEditor extends JPanel {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method checks if inputed values are valid.
	 * 
	 * @throws JVDrawException if values are not valid
	 */
	public abstract void checkEditing() throws JVDrawException;

	/**
	 * Method accepts changed made on object.
	 */
	public abstract void acceptEditing();

}
