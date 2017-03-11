package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling illegal positions for ships.
 * 
 * @version 1.0
 * @author Michiel
 *
 */
public class IllegalPositionException extends Exception {

	private double xPosition;
	private double yPosition;

	/**
	 * Initialize this new illegal position exception with given value.
	 * 
	 * @param xPosition
	 * 		  The xPosition for this new illegal position exception.
	 * @param yPosition
	 * 		  The yPosition for this new illegal position exception.
	 * @post The position of this new illegal position exception is equal to
	 * 		 the given positions.
	 * 		| new.getValue()==Value()	
	 */

	public IllegalPositionException(double xPosition, double yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Return the xPosition registered for this illegal denominator exception.
	 */
	@Basic @Immutable
	public double getxPositon() {
		return this.xPosition;
	}
	
	/**
	 * Return the yPosition registered for this illegal denominator exception.
	 */
	@Basic @Immutable
	public double getyPositon() {
		return this.yPosition;
	}
	
	
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 3234089047451603122L;
}
