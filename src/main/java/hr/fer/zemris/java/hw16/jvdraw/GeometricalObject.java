package hr.fer.zemris.java.hw16.jvdraw;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents general form of geometrical object that could be drawn on canvas.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public abstract class GeometricalObject {
	
	/**
	 * Listener that listens for object's changes
	 */
	private List<GeometricalObjectListener> listeners = new ArrayList<>();
	
	/**
	 * Method that accepts geometrical object visitor.
	 * 
	 * @param v visitor
	 */
	public abstract void accept(GeometricalObjectVisitor v);

	/**
	 * Method that returns {@link GeometricalObjectEditor} for this geometrical object.
	 * 
	 * @return {@link GeometricalObjectEditor} for this geometrical object
	 */
	public abstract GeometricalObjectEditor createGeometricalObjectEditor();

	/**
	 * Method adds given listener to list of object's listeners.
	 * 
	 * @param l new listener
	 */
	public void addGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.add(l);
	}

	/**
	 * Method removes given listener from list of object's listeners.
	 * 
	 * @param l listener that is removed
	 */
	public void removeGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.remove(l);
	}
	
	/**
	 * Method notifies all listeners that object has been changed.
	 */
	public void fire() {
		listeners.forEach(l -> l.geometricalObjectChanged(this));
	}
	
	/**
	 * Method returns string representation of object.
	 * 
	 * @return string representation of object
	 */
	public abstract String asText();

}
