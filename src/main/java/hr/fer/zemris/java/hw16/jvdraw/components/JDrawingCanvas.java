package hr.fer.zemris.java.hw16.jvdraw.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.Tool;
import hr.fer.zemris.java.hw16.jvdraw.visitors.GeometricalObjectPainter;

/**
 * Class that acts as canvas and draws all geometrical object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class JDrawingCanvas extends JComponent implements DrawingModelListener {

	/**
	 * Drawing model object.
	 */
	private DrawingModel model;
	/**
	 * Current tool that is used for drawing objects.
	 */
	private Tool currentTool;
	/**
	 * Flag that shows if canvas has been modified.
	 */
	private boolean isModified;
	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates new {@link JDrawingCanvas} object.
	 * 
	 * @param model       {@link #model}
	 * @param currentTool {@link #currentTool}
	 */
	public JDrawingCanvas(DrawingModel model, Tool currentTool) {
		this.model = model;
		this.currentTool = currentTool;
		model.addDrawingModelListener(this);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GeometricalObjectPainter painter = new GeometricalObjectPainter(g2d);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0, n = model.getSize(); i < n; i++) {
			model.getObject(i).accept(painter);
		}
		currentTool.paint(g2d);
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		isModified = true;
		repaint();
	}


	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		isModified = true;
		repaint();
	}


	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		isModified = true;
		repaint();
	}
	
	/**
	 * Method sets {@link #currentTool}.
	 * 
	 * @param currentTool new tool
	 */
	public void setCurrentTool(Tool currentTool) {
		this.currentTool = currentTool;
	}
	
	/**
	 * Method checks if canvas has been modified.
	 * 
	 * @return <code>true</code> if canvas has been modified, <br>
	 *   	   <code>false</code> otherwise
	 */
	public boolean isModified() {
		return isModified;
	}
	
	/**
	 * Method sets {@link #isModified}.
	 * 
	 * @param isModified new boolean value for isModified flag
	 */
	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

}
