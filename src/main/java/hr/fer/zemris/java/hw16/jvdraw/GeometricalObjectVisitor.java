package hr.fer.zemris.java.hw16.jvdraw;

import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Line;

/**
 * Interface represents geometrical object visitor (Visitor pattern).
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface GeometricalObjectVisitor {
	
	/**
	 * Method that is called when {@link Line} is visited.
	 * 
	 * @param line line object
	 */
	public abstract void visit(Line line);
	
	/**
	 * Method that is called when {@link Circle} is visited.
	 * 
	 * @param circle circle object
	 */
	public abstract void visit(Circle circle);

	/**
	 * Method that is called when {@link FilledCircle} is visited.
	 * 
	 * @param filledCircle filled circle object
	 */
	public abstract void visit(FilledCircle filledCircle);

}
