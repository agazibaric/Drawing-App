package hr.fer.zemris.java.hw16.jvdraw.models;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw16.jvdraw.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectListener;

/**
 * Class represents implementation of {@link DrawingModel} interface
 * that is used for managing and storing drawn geometrical objects.
 * 
 * @author Ante Gazibaric
 * @version 1.9
 *
 */
public class DocumentModel implements DrawingModel, GeometricalObjectListener {
	
	/**
	 * List of drawn objects.
	 */
	private List<GeometricalObject> objects = new ArrayList<>();
	/**
	 * List of model's listeners.
	 */
	private List<DrawingModelListener> listeners = new ArrayList<>();

	@Override
	public int getSize() {
		return objects.size();
	}

	@Override
	public GeometricalObject getObject(int index) {
		return objects.get(index);
	}

	@Override
	public void add(GeometricalObject object) {
		objects.add(object);
		object.addGeometricalObjectListener(this);
		fireObjectAdded(getSize() - 1, getSize() - 1);
	}

	@Override
	public void addDrawingModelListener(DrawingModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeDrawingModelListener(DrawingModelListener l) {
		int index = listeners.indexOf(l);
		if (index == -1) {
			listeners.remove(l);
		}
	}
	
	/**
	 * Method notifies all model's listeners that new objects are added.
	 * 
	 * @param index0 index of first object that is added
	 * @param index1 index of last object that is added
	 */
	private void fireObjectAdded(int index0, int index1) {
		new ArrayList<>(listeners).forEach(l -> l.objectsAdded(this, index0, index1));
	}
	
	/**
	 * Method notifies all model's listeners that objects have been removed.
	 * 
	 * @param index0 index of first object that is removed
	 * @param index1 index of last object that is removed
	 */
	private void fireObjectRemoved(int index0, int index1) {
		new ArrayList<>(listeners).forEach(l -> l.objectsRemoved(this, index0, index1));
	}
	
	/**
	 * Method notifies all model's listeners about changes on objects.
	 * 
	 * @param index0 index of first object that is changed
	 * @param index1 index of last object that is changed
	 */
	private void fireObjectChanged(int index0, int index1) {
		new ArrayList<>(listeners).forEach(l -> l.objectsChanged(this, index0, index1));
	}

	@Override
	public void geometricalObjectChanged(GeometricalObject o) {
		int index = objects.indexOf(o);
		if (index != -1) {
			fireObjectChanged(index, index);
		}
	}

	@Override
	public void remove(GeometricalObject object) {
		int index = objects.indexOf(object);
		if (index == -1)
			return;
		objects.remove(object);
		fireObjectRemoved(index, index);
	}

	@Override
	public void changeOrder(GeometricalObject object, int offset) {
		int currentIndex = objects.indexOf(object);
		if (currentIndex == -1)
			return;
		
		int newIndex = currentIndex + offset;
		if (newIndex < 0) {
			newIndex = 0;
		} else if (newIndex >= getSize()) {
			newIndex = getSize() - 1;
		}
		
		objects.remove(object);
		objects.add(newIndex, object);
		int index0 = Math.min(newIndex, currentIndex);
		int index1 = Math.max(newIndex, currentIndex);
		fireObjectChanged(index0, index1);
	}

}
