package hr.fer.zemris.java.hw16.jvdraw.visitors;

import java.awt.Rectangle;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectVisitor;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Line;

/**
 * Geometrical object visitor that calculates bounding box
 * which surrounds all drawn objects.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class GeometricalObjectBBCalculator implements GeometricalObjectVisitor {
	
	/**
	 * Rectangle that surrounds whole drawn image.
	 */
	private Rectangle boundingBox;

	@Override
	public void visit(Line line) {
		Rectangle r = getRectForLine(line);
		if (boundingBox == null) {
			boundingBox = r;
		} else {
			setNewBounds(r);
		}
	}

	@Override
	public void visit(Circle circle) {
		Rectangle r = getRectForCircle(circle);
		if (boundingBox == null) {
			boundingBox = r;
		} else {
			setNewBounds(r);
		}
	}

	@Override
	public void visit(FilledCircle filledCircle) {
		Rectangle r = getRectForFilledCircle(filledCircle);
		if (boundingBox == null) {
			boundingBox = r;
		} else {
			setNewBounds(r);
		}
	}
	
	/**
	 * Method sets new bounds for {@link #boundingBox}.
	 * 
	 * @param r new rectangle that needs to be accounted
	 */
	private void setNewBounds(Rectangle r) {
		int x = Math.min(boundingBox.x, r.x);
		int y = Math.min(boundingBox.y, r.y);
		int width = Math.max(boundingBox.x + boundingBox.width, r.x + r.width) - x;
		int height = Math.max(boundingBox.y + boundingBox.height, r.y + r.height) - y;
		
		boundingBox.setBounds(x, y, width, height);
	}
	
	/**
	 * Method returns {@link Rectangle} that surrounds given {@code circle}.
	 * 
	 * @param circle circle for which rectangle is returned
	 * @return       rectangle that surrounds given object
	 */
	private Rectangle getRectForCircle(Circle circle) {
		int rad = circle.getRadius();
		int x = circle.getX() - rad;
		int y = circle.getY() - rad;
		int width = 2 * rad;
		int height = 2 * rad;
		
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Method returns {@link Rectangle} that surrounds given {@code circle}.
	 * 
	 * @param circle circle for which rectangle is returned
	 * @return       rectangle that surrounds given object
	 */
	private Rectangle getRectForFilledCircle(FilledCircle circle) {
		int rad = circle.getRadius();
		int x = circle.getX() - rad;
		int y = circle.getY() - rad;
		int width = 2 * rad;
		int height = 2 * rad;
		
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Method returns {@link Rectangle} that surrounds given {@code line}.
	 * 
	 * @param line line for which rectangle is returned
	 * @return     rectangle that surrounds given object
	 */
	private Rectangle getRectForLine(Line line) {
		int x = Math.min(line.getX1(), line.getX2());
		int y  = Math.min(line.getY1(), line.getY2());
		int width = Math.max(line.getX1(), line.getX2()) - x;
		int height = Math.max(line.getY1(), line.getY2()) - y;
		
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Method returns rectangle that surrounds all drawn objects.
	 * 
	 * @return bounding box of the drawn image
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

}
