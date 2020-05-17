package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.Tool;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Line;

/**
 * Tool used for drawing lines.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class LineTool implements Tool {
	
	/**
	 * Drawing model object.
	 */
	private DrawingModel model;
	/**
	 * Color provider object.
	 */
	private IColorProvider colorProvider;
	/**
	 * Line that is currently drawn.
	 */
	private Line currentLine;

	/**
	 * Constructor that creates new {@link LineTool} object.
	 * 
	 * @param model  	    {@link #model}
	 * @param colorProvider {@link #colorProvider}
	 */
	public LineTool(DrawingModel model, IColorProvider colorProvider) {
		this.model = model;
		this.colorProvider = colorProvider;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Not defined
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Not defined
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (currentLine == null) {
			currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY(), colorProvider.getCurrentColor());
		} else {
			model.add(currentLine);
			currentLine = null;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (currentLine != null) {
			currentLine.setX2(e.getX());
			currentLine.setY2(e.getY());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Not defined
		
	}

	@Override
	public void paint(Graphics2D g2d) {
		if (currentLine != null) {
			g2d.setColor(currentLine.getColor());
			g2d.drawLine(currentLine.getX1(), currentLine.getY1(),
						 currentLine.getX2(), currentLine.getY2());
		}
		
	}

}
