package hr.fer.zemris.java.hw16.jvdraw.visitors;

import java.awt.Graphics2D;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectVisitor;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Line;

/**
 * Geometrical object visitor that knows how to draw all geometrical objects.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class GeometricalObjectPainter implements GeometricalObjectVisitor {

	/**
	 * Graphics object used for drawing.
	 */
	private Graphics2D g2d;
	
	/**
	 * Constructor.
	 * 
	 * @param g2d {@link #g2d}
	 */
	public GeometricalObjectPainter(Graphics2D g2d) {
		this.g2d = g2d;
	}

	@Override
	public void visit(Line line) {
		g2d.setColor(line.getColor());
		g2d.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	}

	@Override
	public void visit(Circle circle) {
		int radius = circle.getRadius();
		g2d.setColor(circle.getColor());
		g2d.drawOval(circle.getX() - radius, circle.getY() - radius, 2 * radius, 2 * radius);
	}

	@Override
	public void visit(FilledCircle filledCircle) {
		int radius = filledCircle.getRadius();
		
		g2d.setColor(filledCircle.getBgColor());
		g2d.fillOval(filledCircle.getX() - radius, filledCircle.getY() - radius, 2 * radius, 2 * radius);
		
		g2d.setColor(filledCircle.getFgColor());
		g2d.drawOval(filledCircle.getX() - radius, filledCircle.getY() - radius, 2 * radius, 2 * radius);
	}

}
