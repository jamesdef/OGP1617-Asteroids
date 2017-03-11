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


	/**Initialize this new illegal ship exception with given ship.
	 * 
	 * @param ship
	 * 		  The ship for this new illegal ship exception.
	 * @post The ship of this new illegal ship exception is esaul
	 * 		 to the given ship.
	 * 		 | new.getship() == ship
	 */
	public IllegalOverlapException(ship){
		this.ship = ship;
	}
	
	/**
	 * Return the overlap registered for this illegal overlap exception.
	 */
	@Basic @Immutable
	public boolean overlap() {
		return this.overlap();
	}
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 6325402349817617717L;

}
