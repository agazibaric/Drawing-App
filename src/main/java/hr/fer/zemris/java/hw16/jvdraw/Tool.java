package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Interface represents general form of tool used for drawing object on mouse events.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 * 
 */
public interface Tool {
	
	/**
	 * Method that is called when mouse is pressed.
	 * 
	 * @param e mouse event
	 */
	public void mousePressed(MouseEvent e);

	/**
	 * Method that is called when mouse is released.
	 * 
	 * @param e mouse event
	 */
	public void mouseReleased(MouseEvent e);

	/**
	 * Method that is called when mouse is clicked.
	 * 
	 * @param e mouse event
	 */
	public void mouseClicked(MouseEvent e);

	/**
	 * Method that is called when mouse is moved.
	 * 
	 * @param e mouse event
	 */
	public void mouseMoved(MouseEvent e);

	/**
	 * Method that is called when mouse is dragged.
	 * 
	 * @param e mouse event
	 */
	public void mouseDragged(MouseEvent e);

	/**
	 * Method that allows tool to draw object using given {@code g2d} graphics object.
	 * 
	 * @param g2d graphics object that is used for drawing
	 */
	public void paint(Graphics2D g2d);
	
}
