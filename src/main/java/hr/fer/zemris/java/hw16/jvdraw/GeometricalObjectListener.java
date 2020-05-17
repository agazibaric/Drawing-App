package hr.fer.zemris.java.hw16.jvdraw;

/**
 * Interface represents general form of listener for geometrical object's changes.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface GeometricalObjectListener {
	
	/**
	 * Method is called when geometrical object is changed.
	 * 
	 * @param o object that is changed
	 */
	public void geometricalObjectChanged(GeometricalObject o);
	
}
