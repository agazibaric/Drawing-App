package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;

/**
 * Interface represents general form of color provider.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface IColorProvider {
	
	/**
	 * Method returns current color.
	 * 
	 * @return current color
	 */
	public Color getCurrentColor();

	/**
	 * Method adds listener for color changes.
	 * 
	 * @param l new listener
	 */
	public void addColorChangeListener(ColorChangeListener l);

	/**
	 * Method removes listener from list of listeners for color changes.
	 * 
	 * @param l listener that is removed
	 */
	public void removeColorChangeListener(ColorChangeListener l);

}
