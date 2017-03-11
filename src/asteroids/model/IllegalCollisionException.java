package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling illegal collision for ships.
 * 
 * @version 1.0
 * @author Michiel
 *
 */

public class IllegalCollisionException extends Exception {


	
	/**Initialize this new illegal ship exception with given ship.
	 * 
	 * @param ship
	 * 		  The ship for this new illegal ship exception.
	 * @post The ship of this new illegal ship exception is esaul
	 * 		 to the given ship.
	 * 		 | new.getship() == ship
	 */
	public IllegalCollisionException(Ship ship1, Ship ship2){
		this.ship1 = ship1;	
		this.ship2 = ship2;
	}

	public Ship ship1;
	public Ship ship2;
	
	
	/**
	 * Return ship1 registered for this illegal collision exception.
	 */
	@Basic @Immutable
	public Ship getship1() {
		return this.ship1;
	}
	
	/**
	 * Return ship2 registered for this illegal collision exception.
	 */
	@Basic @Immutable
	public Ship getship2() {
		return this.ship2;
	}
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 6325402349817617717L;

}
