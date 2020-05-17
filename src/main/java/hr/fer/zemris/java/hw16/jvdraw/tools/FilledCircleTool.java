package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.Tool;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.FilledCircle;

/**
 * Tool used for drawing filled circles.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class FilledCircleTool implements Tool {
	
	/**
	 * Drawing model object.
	 */
	private DrawingModel model;
	/**
	 * Filled circle that is currently being drawn.
	 */
	private FilledCircle currentCircle;
	/**
	 * Background color provider.
	 */
	private IColorProvider bgColorProvider;
	/**
	 * Foreground color provider.
	 */
	private IColorProvider fgColorProvider;

	/**
	 * Constructor that creates new {@link FilledCircleTool} object.
	 * 
	 * @param model  		  {@link #model}
	 * @param bgColorProvider {@link #bgColorProvider}
	 * @param fgColorProvider {@link #fgColorProvider}
	 */
	public FilledCircleTool(DrawingModel model, IColorProvider bgColorProvider, IColorProvider fgColorProvider) {
		this.model = model;
		this.bgColorProvider = bgColorProvider;
		this.fgColorProvider = fgColorProvider;
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
			Color bgColor = bgColorProvider.getCurrentColor();
			Color fgColor = fgColorProvider.getCurrentColor();
			currentCircle = new FilledCircle(e.getX(), e.getY(), 0, bgColor, fgColor);
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
			
			g2d.setColor(currentCircle.getBgColor());
			g2d.fillOval(currentCircle.getX() - r, currentCircle.getY() - r, 2 * r, 2 * r);
			
			g2d.setColor(currentCircle.getFgColor());
			g2d.drawOval(currentCircle.getX() - r, currentCircle.getY() - r, 2 * r, 2 * r);
		}
	}

}
