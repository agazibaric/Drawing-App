package hr.fer.zemris.java.hw16.jvdraw.geomeditors;

import hr.fer.zemris.java.hw16.jvdraw.GeometricalObjectEditor;

/**
 * Class represents util for {@link GeometricalObjectEditor}.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class EditorUtil {
	
	/**
	 * Pattern for color input.
	 */
	private static final String COLOR_PATTERN = "\\d+,\\d+,\\d+";
	
	/**
	 * Method checks if given color as {@code value} is in valid format.
	 * 
	 * @param name  name of color
	 * @param value inputed value
	 * @param sb    string builder needed for constructing message for user
	 */
	public static void checkColor(String name, String value, StringBuilder sb) {
		if (!value.matches(COLOR_PATTERN)) {
			sb.append(name + ", ");
		}
	}
	
	/**
	 * Method checks if given value is valid coordinate (integer value).
	 * 
	 * @param name  name of coordinate or property
	 * @param value value of the property
	 * @param sb    string builder needed for constructing message for user
	 */
	public static void checkCoordinate(String name, String value, StringBuilder sb) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			sb.append(name + ", ");
		}
	}
	
	/**
	 * Method returns color components in integer array (red, green blue).
	 * 
	 * @param color color that is parsed
	 * @return      color components in integer array
	 */
	public static Integer[] getColorComponents(String color) {
		String[] parts = color.split(",");
		return new Integer[] {
				Integer.parseInt(parts[0]),
				Integer.parseInt(parts[1]),
				Integer.parseInt(parts[2])
		};
	}

}
