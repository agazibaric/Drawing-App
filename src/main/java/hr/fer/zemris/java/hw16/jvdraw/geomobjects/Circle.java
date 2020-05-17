package hr.fer.zemris.java.hw16.jvdraw.geomobjects;

import java.awt.Color;
import java.awt.Point;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectVisitor;
import hr.fer.zemris.java.hw16.jvdraw.geomeditors.CircleEditor;

/**
 * Class represents circle geometrical object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class Circle extends GeometricalObject {
	
	/**
	 * X value of the circle's center point.
	 */
	private int x;
	/**
	 * Y value of the circle's center point.
	 */
	private int y;
	/**
	 * Circle's radius.
	 */
	private int radius;
	/**
	 * Circle's color.
	 */
	private Color color;

	/**
	 * Constructor.
	 * 
	 * @param x      {@link #x}
	 * @param y		 {@link #y}	
	 * @param radius {@link #radius}
	 * @param color  {@link #color}
	 */
	public Circle(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	/**
	 * Constructor.
	 * 
	 * @param center center point
	 * @param radius {@link #radius}
	 * @param color  {@link #color}
	 */
	public Circle(Point center, int radius, Color color) {
		this(center.x, center.y, radius, color);
	}
	
	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);
	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new CircleEditor(this);
	}
	
	/**
	 * Method returns circle's color.
	 * 
	 * @return circle's color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Method sets circle's color.
	 * 
	 * @param color new color
	 */
	public void setColor(Color color) {
		this.color = color;
		fire();
	}

	/**
	 * Method returns x value of the circle's center point.
	 * 
	 * @return x value of the circle's center point
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method sets x value of the circle's center point.
	 * 
	 * @param x new x value of the circle's center point
	 */
	public void setX(int x) {
		this.x = x;
		fire();
	}

	/**
	 * Method returns y value of the circle's center point.
	 * 
	 * @return y value of the circle's center point.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Method sets y value of the circle's center point.
	 * 
	 * @param y new y value of the circle's center point.
	 */
	public void setY(int y) {
		this.y = y;
		fire();
	}

	/**
	 * Method returns circle's radius.
	 * 
	 * @return circle's radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Method sets circle's radius.
	 * 
	 * @param radius new circle's radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
		fire();
	}
	
	
	@Override
	public String toString() {
		return String.format("Circle (%d,%d), %d", x, y, radius);
	}

	@Override
	public String asText() {
		return String.format("CIRCLE %d %d %d %d %d %d", x, y, radius,
				color.getRed(), color.getGreen(), color.getBlue());
	}

}
