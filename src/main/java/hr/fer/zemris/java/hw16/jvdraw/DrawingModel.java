package hr.fer.zemris.java.hw16.jvdraw;

/**
 * Interface represents form of model 
 * that contains all objects which are drawn.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface DrawingModel {

	/**
	 * Returns number of drawn objects.
	 * 
	 * @return number of drawn objects.
	 */
	int getSize();

	/**
	 * Method returns {@link GeometricalObject} at given {@code index}.
	 * 
	 * @param index index of object that is returned
	 * @return      object at given index
	 */
	GeometricalObject getObject(int index);

	/**
	 * Method adds object to the model's list of objects.
	 * 
	 * @param object new object that is drawn
	 */
	void add(GeometricalObject object);

	/**
	 * Method adds listener of model's changes.
	 * 
	 * @param l new listener
	 */
	void addDrawingModelListener(DrawingModelListener l);

	/**
	 * Method removes given listener from model's listeners.
	 * 
	 * @param l listener that is removed
	 */
	void removeDrawingModelListener(DrawingModelListener l);
	
	/**
	 * Method removes given {@code object} from model's list of objects.
	 * 
	 * @param object object that is removed
	 */
	void remove(GeometricalObject object);
	
	/**
	 * Method changes order of given {@code object} in model's list of objects.
	 * 
	 * @param object object whose place is changed
	 * @param offset offset by which object's place is changed
	 */
	void changeOrder(GeometricalObject object, int offset);

}
