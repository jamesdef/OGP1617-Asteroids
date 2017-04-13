package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;


/**
 * A class for dealing with bullets, which are a kind of entity in space. These have a certain position, velocity, radius, speed and orientation.
 * The bullet also has a mass and a certain density.
 * 
 * 
 * @invar   The highest possible absolute, total velocity is lower than a certain maximum the ship can never exceed this speed.
 * 	      	|!exceedsMaxVelocity(getxVelocity(), getyVelocity())
 *  
 * @invar	The orientation of the ship must be a valid value.
 * 			|isValidOrientation(getOrientation())
 * 
 * @invar 	The radius of each ship must be a valid value.
 * 			|isValidRadius(getRadius())
 * 
 * @invar   The coordinates of a ship must be finit numbers.
 * 			|isValidPosition(getxPosition,getyPosition);
 *   
 * @version 2.0     
 * @author James Defauw & Michiel De Koninck

 */
public class Bullet extends Entity {
	
	public Bullet(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalPositionException, IllegalRadiusException{
		super(xPosition, yPosition, xVelocity, yVelocity, radius, orientation);
		setRadius(radius);
	}
	
	// Initialising Variables & Defaults
	
	/**
	 * Variable registering the minimum allowed Radius.
	 * The minimum radius may change in the future. 
	 * But it will always remain the same for all Bullets.
	 */
	protected static double Min_Radius = 1.0;
	
	/**
	 * Check whether the Minimum radius is a valid limit.
	 * 
	 * @param Min_Radius
	 * 		  The minimum radius to check.
	 * @return Whether the minimum radius is positive or not.
	 * 			| result == (Min_Radius > 0)
	 */
	public static boolean isValidMinimumRadius(double Min_Radius){
		return (Min_Radius > 0);
	}
	
	/**
	 * Variable registering the radius of this Ship.
	 * @Override
	 */
	protected double radius = Min_Radius;

	
	/**
	 * Variable registering the default density of a bullet.
	 */
	protected final static double Default_Density = 7.8*(Math.pow(10, 12));
	
	
	/**
	 * Variable registering the density of this bullet.
	 * @Override
	 */
	private double density = Default_Density;
	
	/**
	 * Variable registering the mass of this bullet.
	 * @Override
	 */
	private double mass = Default_Mass;
	
	/**
	 * Variable registering the Default_Mass of a bullet
	 */
	protected final static double Default_Mass = Default_Density*(4/3)*Math.PI*(Math.pow(Min_Radius, 3));
	
	/**
	 * Field initialising the existence of ship.
	 * Initialised to a value of null.
	 */
	private Ship ship = null;

	/**
	 * Field initialising the existence of World.
	 * Initialised to a value of null.
	 */
	private World world = null;
    
	

	// Getters
	
	/**
	 * Returns the world to which this bullet belongs.
	 * @return the world to which this bullet belongs.
	 */
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Returns the ship to which this bullet belongs.
	 * @return the ship to which this bullet belongs.
	 */
	@Basic
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * Returns the mass of this bullet.
	 * @return the mass of this bullet.
	 */
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Returns the density of this ship.
	 * @return the density of this ship.
	 */
	public double getDensity(){
		return this.density;
	}
	
	// Setters
	
	 /**
     * This method makes it possible for the user to change the lower bound
     * imposed upon ships, for all ships.
     * 
     * @param Lower_Bound
     * 		  The new minimum radius.
     * 
     * @post The new universal lower bound for the radius is equal to the given value.
     * 		 |new.Min_Radius == Lower_Bound
     * 
     * @throws IllegalArgumentException
     * 		   The given argument is not valid.
     * 		   | !(Lower_bound > 0)
     */
    public final void setMin_Radius(double Lower_Bound){
    	if (Lower_Bound > 0){
    		Bullet.Min_Radius = Lower_Bound;
    	}
    	else{
    		throw new IllegalArgumentException(Double.toString(Lower_Bound));
    	}
    }
    

	/** 
	 * Sets the radius to the given Value, if it is valid.
	 * 
	 * @param radius
	 * 		  The new, given radius of the ship.
	 * 
	 * @post The radius of the ship is now equal to the given, valid radius.
	 * 		|new.getRadius() == radius	
	 * 
	 * @throws  IllegalRadiusException
	 * 		   The given radius is not a valid radius.
	 * 		   | ! isValidRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalRadiusException{
		if (!isValidRadius(radius)){
			throw new IllegalRadiusException(radius);}
		this.radius = radius;
	}

	/** 
	 * Checks whether the given radius has a valid value.
	 * 
	 * @param  radius
	 * 		   The radius of the ship.
	 * 
	 * @return True if the radius exceeds the minimal radius
	 * 		   false if the radius is less than the minimal_radius. 
	 * 		   Or if the radius is Infinity or not a number.
	 * 		   | radius >= getMin_Radius;
	 */
	public static boolean isValidRadius(double radius){
		return (radius >= Min_Radius && (!Double.isNaN(radius) && radius != Double.POSITIVE_INFINITY));
	}
	
	// BELANGRIJK CONSISTENTIE VAN BINDIGEN!
	
	/**
	 * Method that sets a ship as the owner of this bullet.
	 * @param ship
	 * 		  The ship that we try to set as owner
	 * 
	 * @post  if the bullet already has an owner, nothing happens.
	 * 		  |if this.hasOwner()
	 * 			|then new.ship = this.ship
	 * 
	 * @post if the bullet does not have an owner yet, 
	 * 		 the bullet gets the given ship as its owner.
	 * 		 |if !this.hasOwner()
	 * 			|then new.ship = ship
	 */
	public void setShip(Ship ship){
		if (!this.hasOwner()){
			this.ship = ship;
		}
	}
	
	/**
	 * Returns true is this bullet has an owner. 
	 * That is, if this  bullet has a source from which it came.
	 * @return True if it has an owner
	 * 		   False if it does not
	 */
	public boolean hasOwner(){
		return (this.ship != null);
	}
	
	// Als een kogel een schip als bron heeft, dan 
	// zal het in dezelfde wereld zitten als dit schip.
	// Die consistentie moet dus gecontroleerd worden.
	public void setWorld(World world){
		this.world = world;
		
	}
	
	
/*
 * position x,y
 * velocity x, y
 * speed
 * maxSpeed
 * radius
 * minRadius 1
 * mass
 * density
 * hasBeenFired()
 * 
 * getWorld(){
 * 
 * }
 * 
 * getSource(){
 * 
 * }
 * 
 * Either loaded or fired by the ship which it has as source.
 * getState(){
 * }
 * 
 * 
 * 
 */
}
