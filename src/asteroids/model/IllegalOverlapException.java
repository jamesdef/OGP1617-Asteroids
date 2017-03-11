package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling illegal overlap for ships.
 * 
 * @version 1.0
 * @author Michiel
 *
 */

public class IllegalOverlapException extends Exception {
	/**
	 * Variable registering the radius involved in this illegal radius
	 * exception.
	 */
	private double radius;

	/**Initialize this new illegal radius exception with given radius.
	 * 
	 * @param radius
	 * 		  The radius for this new illegal radius exception.
	 * @post The radius of this new illegal radius exception is esaul
	 * 		 to the given radius.
	 * 		 | new.getRadius() == Radius
	 */
	public IllegalRadiusException(double radius){
		this.radius = radius;
	}
	
	/**
	 * Return the radius registered for this illegal radius exception.
	 */
	@Basic @Immutable
	public double radius() {
		return this.radius;
	}
	

}
