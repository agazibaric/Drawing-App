package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.Tool;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Circle;

/**
 * Tool used for drawing circles.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CircleTool implements Tool {
	
	/**
	 * Drawing model object.
	 */
	private DrawingModel model;
	/**
	 * Color provider.
	 */
	private IColorProvider colorProvider;
	/**
	 * Circle that is currently being drawn.
	 */
	private Circle currentCircle;

	/**
	 * Constructor that creates new {@link CircleTool} object.
	 * 
	 * @param model  	    {@link #model}
	 * @param colorProvider {@link #colorProvider}
	 */
	public CircleTool(DrawingModel model, IColorProvider colorProvider) {
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
		if (currentCircle == null) {
			currentCircle = new Circle(e.getX(), e.getY(), 0, colorProvider.getCurrentColor());
		} else {
			model.add(currentCircle);
			currentCircle = null;
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (currentCircle != null) {
			int x1 = currentCircle.getX();
			int y1 = currentCircle.getY();
			int x2 = e.getX();
			int y2 = e.getY();
			int radius = (int) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
			currentCircle.setRadius(radius);
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Not defined
		
	}

	@Override
	public void paint(Graphics2D g2d) {
		if (currentCircle != null) {
			int r = currentCircle.getRadius();
			g2d.setColor(currentCircle.getColor());
			g2d.drawOval(currentCircle.getX() - r, currentCircle.getY() - r, 2 * r, 2 * r);
		}
		
	}

}
