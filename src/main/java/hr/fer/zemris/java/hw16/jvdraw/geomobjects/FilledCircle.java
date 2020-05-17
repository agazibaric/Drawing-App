package hr.fer.zemris.java.hw16.jvdraw.geomobjects;

import java.awt.Color;
import java.awt.Point;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectVisitor;
import hr.fer.zemris.java.hw16.jvdraw.geomeditors.FilledCircleEditor;

/**
 * Class represents filled circle geometrical object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class FilledCircle extends GeometricalObject {
	
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
	 * Circle's background color.
	 */
	private Color bgColor;
	/**
	 * Circle's foreground color.
	 */
	private Color fgColor;

	/**
	 * Constructor.
	 * 
	 * @param x		  {@link #x}
	 * @param y		  {@link #y}
	 * @param radius  {@link #radius}
	 * @param bgColor {@link #bgColor}
	 * @param fgColor {@link #fgColor}
	 */
	public FilledCircle(int x, int y, int radius, Color bgColor, Color fgColor) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.bgColor = bgColor;
		this.fgColor = fgColor;
	}

	/**
	 * Constructor.
	 * 
	 * @param center  circle's center point
	 * @param radius  {@link #radius}
	 * @param bgColor {@link #bgColor}
	 * @param fgColor {@link #fgColor}
	 */
	public FilledCircle(Point center, int radius, Color bgColor, Color fgColor) {
		this(center.x, center.y, radius, bgColor, fgColor);
	}

	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);
	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new FilledCircleEditor(this);
	}
	
	/**
	 * Method returns circle's background color.
	 * 
	 * @return circle's background color
	 */
	public Color getBgColor() {
		return bgColor;
	}
	
	/**
	 * Method sets circle's background color.
	 * 
	 * @param bgColor new circle's background color
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
		fire();
	}
	
	/**
	 * Method returns circle's foreground color.
	 * 
	 * @return circle's foreground color
	 */
	public Color getFgColor() {
		return fgColor;
	}
	
	/**
	 * Method sets circle's foreground color.
	 * 
	 * @param fgColor new circle's foreground color
	 */
	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
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
	 * @param x new value of the circle's center point
	 */
	public void setX(int x) {
		this.x = x;
		fire();
	}

	/**
	 * Method returns y value of the circle's center point.
	 * 
	 * @return y value of the circle's center point
	 */
	public int getY() {
		return y;
	}

	/**
	 * Method sets y value of the circle's center point.
	 * 
	 * @param y new y value of the circle's center point
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
		return String.format("Filled circle (%d,%d), %d, #%s", x, y, radius,
				Integer.toHexString(bgColor.getRGB()).substring(2).toUpperCase());
	}
	
	@Override
	public String asText() {
		return String.format("FCIRCLE %d %d %d %d %d %d %d %d %d", x, y, radius,
				fgColor.getRed(), fgColor.getGreen(), fgColor.getBlue(),
				bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
	}
	

}
