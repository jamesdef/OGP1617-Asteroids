package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling illegal duration for moving entities.
 * 
 * @version 1.0
 * @author Michiel
 *
 */

public class IllegalDurationException extends Exception{

	/**
	 * Variable registering the duration involved in this illegal duration
	 * exception.
	 */
	private double duration;

	/**Initialize this new illegal duration exception with given duration.
	 * 
	 * @param duration
	 * 		  The duration for this new illegal duration exception.
	 * @post The duration of this new illegal duration exception is esaul
	 * 		 to the given duration.
	 * 		 | new.getduration() == duration
	 */
	public IllegalDurationException(double duration){
		this.duration = duration;
	}
	
	/**
	 * Return the duration registered for this illegal duration exception.
	 */
	@Basic @Immutable
	public double duration() {
		return this.duration;
	}
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 3521683031467143975L;
	

}
