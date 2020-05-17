package hr.fer.zemris.java.hw16.jvdraw.geomeditors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.JVDrawException;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Circle;

/**
 * Class represents circle editor object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class CircleEditor extends GeometricalObjectEditor {

	/**
	 * Circle whose properties are shown.
	 */
	private Circle circle;
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
	 * Text field for color.
	 */
	private JTextField textColor;
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
	 * Constructor that creates new {@link CircleEditor} object.
	 * 
	 * @param circle {@link #circle}
	 */
	public CircleEditor(Circle circle) {
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
		labelPanel.setLayout(new GridLayout(4, 1));
		textPanel.setLayout(new GridLayout(4, 1));
		
		labelPanel.add(new JLabel("X: "));
		labelPanel.add(new JLabel("Y: "));
		labelPanel.add(new JLabel("Radius: "));
		labelPanel.add(new JLabel("Color: "));
		
		Color c = circle.getColor();
		textX = new JTextField(String.valueOf(circle.getX()));
		textY = new JTextField(String.valueOf(circle.getY()));
		textR = new JTextField(String.valueOf(circle.getRadius()));
		textColor = new JTextField(String.format("%d,%d,%d", c.getRed(), c.getGreen(), c.getBlue()));
		
		textPanel.add(textX);
		textPanel.add(textY);
		textPanel.add(textR);
		textPanel.add(textColor);
		
	}

	@Override
	public void checkEditing() {
		sb.append(INIT_STRING);
		EditorUtil.checkCoordinate("X", textX.getText(), sb);
		EditorUtil.checkCoordinate("Y", textY.getText(), sb);
		EditorUtil.checkCoordinate("Radius", textR.getText(), sb);
		EditorUtil.checkColor("Color", textColor.getText().trim(), sb);
		
		if (sb.length() > INIT_STRING.length()) {
			throw new JVDrawException(sb.substring(0, sb.length() - 2));
		}
	}

	@Override
	public void acceptEditing() {
		circle.setX(Integer.valueOf(textX.getText()));
		circle.setY(Integer.valueOf(textY.getText()));
		circle.setRadius(Integer.valueOf(textR.getText()));
		Integer[] components = EditorUtil.getColorComponents(textColor.getText());
		Color color = new Color(components[0], components[1], components[2]);
		circle.setColor(color);
	}

}
