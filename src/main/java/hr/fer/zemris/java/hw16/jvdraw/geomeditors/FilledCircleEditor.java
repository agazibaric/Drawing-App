package hr.fer.zemris.java.hw16.jvdraw.geomeditors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.JVDrawException;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.FilledCircle;

/**
 * Class represents filled circle editor.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class FilledCircleEditor extends GeometricalObjectEditor {

	/**
	 * Filled circle whose properties are shown.
	 */
	private FilledCircle circle;
	/**
	 * Text field for x value.
	 */
	private JTextField textX;
	/**
	 * Text field for y value.
	 */
	private JTextField textY;
	/**
	 * Text field for radius.
	 */
	private JTextField textR;
	/**
	 * Text field for background color.
	 */
	private JTextField textBgColor;
	/**
	 * Text field for foreground color.
	 */
	private JTextField textFgColor;
	/**
	 * String builder object used for generating message to user.
	 */
	private StringBuilder sb = new StringBuilder();
	/**
	 * Initial value of string builder.
	 */
	private static final String INIT_STRING = "Invalid input for: ";
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates new {@link FilledCircleEditor} object.
	 * 
	 * @param circle {@link #circle}
	 */
	public FilledCircleEditor(FilledCircle circle) {
		this.circle = circle;
		initPanel();
	}
	
	/**
	 * Method initializes editor's panel.
	 */
	private void initPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel labelPanel = new JPanel();
		JPanel textPanel = new JPanel();
		this.add(labelPanel, BorderLayout.WEST);
		this.add(textPanel, BorderLayout.EAST);
		labelPanel.setLayout(new GridLayout(5, 1));
		textPanel.setLayout(new GridLayout(5, 1));
		
		labelPanel.add(new JLabel("X: "));
		labelPanel.add(new JLabel("Y: "));
		labelPanel.add(new JLabel("Radius: "));
		labelPanel.add(new JLabel("Background color: "));
		labelPanel.add(new JLabel("Foreground color: "));
		
		Color bgC = circle.getBgColor();
		Color fgC = circle.getFgColor();
		textX = new JTextField(String.valueOf(circle.getX()));
		textY = new JTextField(String.valueOf(circle.getY()));
		textR = new JTextField(String.valueOf(circle.getRadius()));
		textBgColor = new JTextField(String.format("%d,%d,%d", bgC.getRed(), bgC.getGreen(), bgC.getBlue()));
		textFgColor = new JTextField(String.format("%d,%d,%d", fgC.getRed(), fgC.getGreen(), fgC.getBlue()));
		
		textPanel.add(textX);
		textPanel.add(textY);
		textPanel.add(textR);
		textPanel.add(textBgColor);
		textPanel.add(textFgColor);
		
	}

	@Override
	public void checkEditing() {
		sb.append(INIT_STRING);
		EditorUtil.checkCoordinate("X", textX.getText(), sb);
		EditorUtil.checkCoordinate("Y", textY.getText(), sb);
		EditorUtil.checkCoordinate("Radius", textR.getText(), sb);
		EditorUtil.checkColor("Background color", textBgColor.getText().trim(), sb);
		EditorUtil.checkColor("Foreground color", textFgColor.getText().trim(), sb);
		
		if (sb.length() > INIT_STRING.length()) {
			throw new JVDrawException(sb.substring(0, sb.length() - 2));
		}
	}

	@Override
	public void acceptEditing() {
		circle.setX(Integer.valueOf(textX.getText()));
		circle.setY(Integer.valueOf(textY.getText()));
		circle.setRadius(Integer.valueOf(textR.getText()));
		Integer[] bgComponents = EditorUtil.getColorComponents(textBgColor.getText());
		Integer[] fgComponents = EditorUtil.getColorComponents(textFgColor.getText());
		Color bgColor = new Color(bgComponents[0], bgComponents[1], bgComponents[2]);
		Color fgColor = new Color(fgComponents[0], fgComponents[1], fgComponents[2]);
		circle.setBgColor(bgColor);
		circle.setFgColor(fgColor);
	}

}
