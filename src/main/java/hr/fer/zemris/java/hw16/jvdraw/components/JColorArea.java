package hr.fer.zemris.java.hw16.jvdraw.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;

import hr.fer.zemris.java.hw16.jvdraw.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.IColorProvider;

/**
 * Class represents color component that offers user to chose color
 * and also it acts like color provider.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class JColorArea extends JComponent implements IColorProvider {
	
	/**
	 * Currently selected color.
	 */
	private Color selectedColor;
	/**
	 * List of listeners for color changes.
	 */
	private List<ColorChangeListener> listeners = new ArrayList<>();
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default dimensions.
	 */
	private static final Dimension DEFAULT_DIMENSION = new Dimension(15, 15);
	
	/**
	 * Constructor that creates new {@link JColorArea} object.
	 * 
	 * @param selectedColor currently selected color
	 * @param mainFrame     main frame
	 */
	public JColorArea(Color selectedColor, JFrame mainFrame) {
		this.selectedColor = selectedColor;
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(mainFrame, "Choose color", Color.BLACK);
				if (newColor != null) {
					setSelectedColor(newColor);
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(selectedColor);
		g2d.fillRect(0, 0, DEFAULT_DIMENSION.width, DEFAULT_DIMENSION.height);
		
	}

	@Override
	public Dimension getPreferredSize() {
		return DEFAULT_DIMENSION;
	}

	@Override
	public Color getCurrentColor() {
		return selectedColor;
	}

	@Override
	public void addColorChangeListener(ColorChangeListener l) {
		listeners.add(l);
		
	}

	@Override
	public void removeColorChangeListener(ColorChangeListener l) {
		listeners.remove(l);
	}
	
	/**
	 * Method sets currently selected color to the given {@code newColor}.
	 * 
	 * @param newColor new color
	 */
	public void setSelectedColor(Color newColor) {
		fire(this.selectedColor, newColor);
		this.selectedColor = newColor;
		repaint();
	}
	
	/**
	 * Method notifies all listeners for color changed that color has been changed.
	 * 
	 * @param oldColor old color
	 * @param newColor new color
	 */
	private void fire(Color oldColor, Color newColor) {
		new ArrayList<>(listeners).forEach(l -> l.newColorSelected(this, oldColor, newColor));
	}
	
}
