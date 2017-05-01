package asteroids.model.exceptions;

import asteroids.model.*;
/** 
 * A class of exceptions signaling an illegal entity argument.
 * 
 * @version 2.0
 * @author Michiel
 */
public class IllegalEntityException extends RuntimeException {


	/**
	 * Initialize this new entity exception with given entity.
	 * 
	 * @param 	entity
	 * 			The entity for this new illegal entity exception.
	 * @post	The entity of this new illegal entity exception is the given entity.
	 * 			| new.getEntity() == entity
	 */
	public IllegalEntityException(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Return the entity of this illegal entity exception.
	 */
	public Entity getEntity() {
		return this.entity;
	}

	/**
	 * A variable registering the entity of this illegal entity exception.
	 */
	private final Entity entity;
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = -2554669130350570523L;

}