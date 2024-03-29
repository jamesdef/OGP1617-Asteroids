package asteroids.model.exceptions;

import asteroids.model.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal radii for ships.
 * 
 * @version 1.0
 * @author Michiel
 *
 */
public class IllegalRadiusException extends Exception{
 
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
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 4742891826762255005L;
	
} 
