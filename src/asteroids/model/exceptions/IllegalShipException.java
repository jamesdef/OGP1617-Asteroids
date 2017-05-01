package asteroids.model.exceptions;

import asteroids.model.*;
/** 
 * A class for signaling illegal ships.
 * 
 * @version 2.0
 * @author Michiel
 */

public class IllegalShipException extends Exception {
	
	
	/**
	 * Initialize this new ship exception with given ship.
	 * 
	 * @param 	ship
	 * 			The ship for this new illegal ship exception.
	 * @post	The ship of this new illegal ship exception is the given ship.
	 * 			| new.getShip() == ship
	 */
	public IllegalShipException(Ship ship) {
		this.ship = ship;
	}
	
	/**
	 * Return the ship registered for this illegal ship exception.
	 */
	public Ship getShip() {
		return this.ship;
	}

	/**
	 * A variable registering the ship of this illegal ship exception.
	 */
	private final Ship ship;
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = -393898663851222243L;

}
