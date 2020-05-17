package hr.fer.zemris.java.hw16.jvdraw;

/**
 * Interface represents general form of {@link DrawingModel} object's listener.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface DrawingModelListener {

	/**
	 * Method is called when objects are added to model.
	 * 
	 * @param source drawing model object
	 * @param index0 index of first object in list that is added
	 * @param index1 index of last object in list that is added
	 */
	public void objectsAdded(DrawingModel source, int index0, int index1);

	/**
	 * Method is called when objects are removed from model.
	 * 
	 * @param source drawing model object
	 * @param index0 index of first object in list that is removed
	 * @param index1 index of last object in list that is removed
	 */
	public void objectsRemoved(DrawingModel source, int index0, int index1);

	/**
	 * Method is called when objects in model's list have been changed.
	 * 
	 * @param source drawing model object
	 * @param index0 index of first object in list that is changed
	 * @param index1 index of last object in list that is changed
	 */
	public void objectsChanged(DrawingModel source, int index0, int index1);

}
