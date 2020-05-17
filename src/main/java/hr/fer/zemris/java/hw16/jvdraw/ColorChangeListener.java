package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;

/**
 * Interface represents form for color change listener.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface ColorChangeListener {
	
	/**
	 * Method is called when color has been changed.
	 * 
	 * @param source   color provider whose color is changed
	 * @param oldColor old color
	 * @param newColor new color
	 */
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor);
	
}
