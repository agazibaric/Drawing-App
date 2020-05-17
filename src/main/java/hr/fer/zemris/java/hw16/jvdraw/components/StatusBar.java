package hr.fer.zemris.java.hw16.jvdraw.components;

import java.awt.Color;

import javax.swing.JLabel;

import hr.fer.zemris.java.hw16.jvdraw.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.IColorProvider;

/**
 * Status bar that shows current foreground and background colors.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class StatusBar extends JLabel implements ColorChangeListener {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Foreground color provider.
	 */
	private IColorProvider fgColorProvider;
	/**
	 * Background color provider.
	 */
	private IColorProvider bgColorProvider;
	/**
	 * Foreground color.
	 */
	private Color fgColor;
	/**
	 * Background color.
	 */
	private Color bgColor;
	
	/**
	 * Contructor that creates new {@link StatusBar} object.
	 * 
	 * @param fgColorProvider {@link #fgColorProvider}
	 * @param bgColorProvider {@link #bgColorProvider}
	 */
	public StatusBar(IColorProvider fgColorProvider, IColorProvider bgColorProvider) {
		this.fgColorProvider = fgColorProvider;
		this.bgColorProvider = bgColorProvider;
		this.fgColor = fgColorProvider.getCurrentColor();
		this.bgColor = bgColorProvider.getCurrentColor();
		setupListeners();
		setText(getStatusBarText());
	}
	
	/**
	 * Method setups listeners for color changes of color providers.
	 */
	private void setupListeners() {
		fgColorProvider.addColorChangeListener(this);
		bgColorProvider.addColorChangeListener(this);
	}

	@Override
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor) {
		if (source == fgColorProvider) {
			fgColor = newColor;
		} else {
			bgColor = newColor;
		}
		setText(getStatusBarText());
	}
	
	/**
	 * Method returns status bar informations as string.
	 * 
	 * @return string representing informations of the status bar
	 */
	private String getStatusBarText() {
		return String.format("Foreground color: (%d, %d, %d), "
						   + "background color: (%d, %d, %d)",
						   fgColor.getRed(), fgColor.getGreen(), fgColor.getBlue(),
						   bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
	}
	
}
