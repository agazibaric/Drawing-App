package hr.fer.zemris.java.hw16.jvdraw.geomobjects;

import java.awt.Color;
import java.awt.Point;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectVisitor;
import hr.fer.zemris.java.hw16.jvdraw.geomeditors.LineEditor;

/**
 * Class represents line geometrical object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class Line extends GeometricalObject {
	
	/**
	 * X value of line's start point.
	 */
	private int x1;
	/**
	 * Y value of line's start point.
	 */
	private int y1;
	/**
	 * X value of line's end point.
	 */
	private int x2;
	/**
	 * Y value of line's end point.
	 */
	private int y2;
	/**
	 * Line's color.
	 */
	private Color color;

	/**
	 * Constructor.
	 * 
	 * @param x1    {@link #x1}
	 * @param y1	{@link #y1}
	 * @param x2	{@link #x2}
	 * @param y2	{@link #y2}
	 * @param color {@link #color}
	 */
	public Line(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	/**
	 * Contructor.
	 * 
	 * @param start start point of the line
	 * @param end   end point of the line
	 * @param color line's color
	 */
	public Line(Point start, Point end, Color color) {
		this(start.x, start.y, end.x, end.y, color);
	}

	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);
	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new LineEditor(this);
	}

	/**
	 * Method returns x value of the line's start point.
	 * 
	 * @return x value of the line's start point
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Method sets x value for the line's start point.
	 * 
	 * @param x1 new x value for the line's start point
	 */
	public void setX1(int x1) {
		this.x1 = x1;
		fire();
	}

	/**
	 * Method returns y value of the line's start point.
	 * 
	 * @return y value of the line's start point
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Method sets y value for the line's start point.
	 * 
	 * @param x1 new y value for the line's start point
	 */
	public void setY1(int y1) {
		this.y1 = y1;
		fire();
	}

	/**
	 * Method returns x value of the line's end point.
	 * 
	 * @return x value of the line's end point
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Method sets x value for the line's end point.
	 * 
	 * @param x1 new x value for the line's end point
	 */
	public void setX2(int x2) {
		this.x2 = x2;
		fire();
	}

	/**
	 * Method returns y value of the line's end point.
	 * 
	 * @return y value of the line's end point
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Method sets y value for the line's end point.
	 * 
	 * @param x1 new y value for the line's end point
	 */
	public void setY2(int y2) {
		this.y2 = y2;
		fire();
	}
	
	/**
	 * Method returns line's color.
	 * 
	 * @return line's color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Method sets line's color.
	 * 
	 * @param color new line's color
	 */
	public void setColor(Color color) {
		this.color = color;
		fire();
	}
	
	@Override
	public String toString() {
		return String.format("Line (%d,%d)-(%d,%d)", x1, y1, x2, y2);
	}

	@Override
	public String asText() {
		return String.format("LINE %d %d %d %d %d %d %d", x1, y1, x2, y2,
				color.getRed(), color.getGreen(), color.getBlue());
	}
	
}
