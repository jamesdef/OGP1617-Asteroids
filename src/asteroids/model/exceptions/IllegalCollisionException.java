package asteroids.model.exceptions;

import asteroids.model.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling illegal collision for entities.
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
	public IllegalCollisionException(Entity entity, Entity other){
		this.entity1 = entity;	
		this.entity2 = other;
	}

	public Entity entity1;
	public Entity entity2;
	
	
	/**
	 * Return ship1 registered for this illegal collision exception.
	 */
	@Basic @Immutable
	public Entity getentity1() {
		return this.entity1;
	}
	
	/**
	 * Return ship2 registered for this illegal collision exception.
	 */
	@Basic @Immutable
	public Entity getentity2() {
		return this.entity2;
	}
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 6325402349817617717L;

}
