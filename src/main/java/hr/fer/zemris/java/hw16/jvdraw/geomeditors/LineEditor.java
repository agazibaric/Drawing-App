package hr.fer.zemris.java.hw16.jvdraw.geomeditors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.JVDrawException;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Line;

/**
 * Class represents line editor.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class LineEditor extends GeometricalObjectEditor {
	
	/**
	 * Line whose properties are shown.
	 */
	private Line line;
	/**
	 * Text field for x1 value.
	 */
	private JTextField textX1;
	/**
	 * Text field for y1 value.
	 */
	private JTextField textY1;
	/**
	 * Text field for x2 value.
	 */
	private JTextField textX2;
	/**
	 * Text field for y2 value.
	 */
	private JTextField textY2;
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
	 * Constructor that creates new {@link LineEditor} object.
	 * 
	 * @param line {@link #line}
	 */
	public LineEditor(Line line) {
		this.line = line;
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
		
		labelPanel.add(new JLabel("X1: "));
		labelPanel.add(new JLabel("Y1: "));
		labelPanel.add(new JLabel("X2: "));
		labelPanel.add(new JLabel("Y2: "));
		labelPanel.add(new JLabel("Color: "));
		
		Color c = line.getColor();
		textX1 = new JTextField(String.valueOf(line.getX1()));
		textY1 = new JTextField(String.valueOf(line.getY1()));
		textX2 = new JTextField(String.valueOf(line.getX2()));
		textY2 = new JTextField(String.valueOf(line.getY2()));
		textColor = new JTextField(String.format("%d,%d,%d", c.getRed(), c.getGreen(), c.getBlue()));
		
		textPanel.add(textX1);
		textPanel.add(textY1);
		textPanel.add(textX2);
		textPanel.add(textY2);
		textPanel.add(textColor);
		
	}

	@Override
	public void checkEditing() {
		sb.append(INIT_STRING);
		EditorUtil.checkCoordinate("X1", textX1.getText(), sb);
		EditorUtil.checkCoordinate("Y1", textY1.getText(), sb);
		EditorUtil.checkCoordinate("X2", textX2.getText(), sb);
		EditorUtil.checkCoordinate("Y2", textY2.getText(), sb);
		EditorUtil.checkColor("Color", textColor.getText().trim(), sb);
		
		if (sb.length() > INIT_STRING.length()) {
			throw new JVDrawException(sb.substring(0, sb.length() - 2));
		}
	}

	@Override
	public void acceptEditing() {
		line.setX1(Integer.valueOf(textX1.getText()));
		line.setY1(Integer.valueOf(textY1.getText()));
		line.setX2(Integer.valueOf(textX2.getText()));
		line.setY2(Integer.valueOf(textY2.getText()));
		Integer[] components = EditorUtil.getColorComponents(textColor.getText());
		Color color = new Color(components[0], components[1], components[2]);
		line.setColor(color);
	}
	
	

}
