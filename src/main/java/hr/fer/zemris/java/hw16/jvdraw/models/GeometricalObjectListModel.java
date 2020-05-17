package hr.fer.zemris.java.hw16.jvdraw.models;

import javax.swing.AbstractListModel;

import hr.fer.zemris.java.hw16.jvdraw.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObject;

/**
 * Class represents list model that extends {@link AbstractListModel}.
 * It is wrapped around {@link DrawingModel} object as adapter.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class GeometricalObjectListModel extends AbstractListModel<GeometricalObject> implements DrawingModelListener {
	
	/**
	 * Drawing model object.
	 */
	private DrawingModel model;
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * 
	 * @param model Drawing model object
	 */
	public GeometricalObjectListModel(DrawingModel model) {
		this.model = model;
		model.addDrawingModelListener(this);
	}

	@Override
	public GeometricalObject getElementAt(int index) {
		return model.getObject(index);
	}

	@Override
	public int getSize() {
		return model.getSize();
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		fireIntervalAdded(source, index0, index1);
	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		fireIntervalRemoved(source, index0, index1);
	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		fireContentsChanged(source, index0, index1);
	}

}
