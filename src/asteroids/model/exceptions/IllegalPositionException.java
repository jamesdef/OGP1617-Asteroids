package asteroids.model.exceptions;

import asteroids.model.*;

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

	
	/**
	 * Variable registering the xPosition involved in this illegal position
	 * exception.
	 */
	private double xPosition;
	
	/**
	 * Variable registering the yPosition involved in this illegal position
	 * exception.
	 */
	private double yPosition;

	/**
	 * Initialize this new illegal position exception with given position.
	 * 
	 * @param xPosition
	 * 		  The xPosition for this new illegal position exception.
	 * @param yPosition
	 * 		  The yPosition for this new illegal position exception.
	 * @post The position of this new illegal position exception is equal to
	 * 		 the given positions.
	 * 		| new.getRadius()==Value	
	 */

	public IllegalPositionException(double xPosition, double yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Return the xPosition registered for this illegal position exception.
	 */
	@Basic @Immutable
	public double getxPositon() {
		return this.xPosition;
	}
	
	/**
	 * Return the yPosition registered for this illegal position exception.
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
