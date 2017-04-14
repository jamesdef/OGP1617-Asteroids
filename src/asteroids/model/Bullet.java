package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class for dealing with bullets, which are a kind of entity in space. These have a certain position, velocity, radius, speed and orientation.
 * The bullet also has a mass and a certain density.
 * A bullet can have an owner: a ship or a world. If it has a ship as its owner, it can not have a world as its owner.
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
	
	
	/**
	 * Terminate this bullet.
	 * 
	 * @post This bullet is now terminated.
	 * 		 |new.isTerminated()
	 * @post The bullet is removed from any ship or world to which it belongs.
	 * 		 Its reference is also deleted within its 'owner'.
	 * 		 | @see implementation
	 */
	public void terminate(){
		//if the bullet belongs to a ship, the bullet is removed from the collection of that ship.
        if (this.getShip() != null)
            this.getShip().removeBullet(this);
        //if the bullet belongs to a world, the bullet is removed from the collection of that world.
        else if (this.getWorld() != null)
            this.getWorld().removeEntity(this);

        //The bullet no longer references its source ship.
        this.setSource(null);
        // The bullet is terminated as an entity, now that all other links have been undone.
        super.terminate();
    }
	
	/**
     * Returns the source that fired this bullet. 
     * If it exists; this will always be a ship.
     */
    @Basic
    public Ship getSource() {
        return this.source;
    }

    /**
	 * Set the source of this bullet to a given ship.
	 * 
	 * @param	source
	 * 			The ship that fired this bullet.
	 * @post	The source of this bullet is the given ship.
	 * 			| new.getSource() == source
	 * @throws 	IllegalArgumentException
	 * 			If this bullet 
	 * 				-already belongs to a world
	 * 				-already is loaded/fired by a ship
	 * 		    |(this.getWorld()!=null||this.getShip()!=null||this.getSource()!=null)
	 */
	public void setSource(Ship source) throws IllegalArgumentException {
		if (this.getWorld()!=null||this.getShip()!=null||this.getSource()!=null)
			throw new IllegalArgumentException();
		if (source==null)
			this.source=null;
		else
			this.source = source;
	}

    /**
     * Variable referencing the source ship (that fired the bullet) of the bullet.
     * In default; set to null.
     */
    private Ship source = null;

	
	/**
	 * Check whether this bullet has a proper owner. 
	 * Meaning that it can not belong to both a world and a ship.
	 * @return True if and only if this bullet has only one owner.
	 * 		   | result !((this.getShip() != null && this.getWorld() != null)
	 */
	public boolean hasProperOwner(){
		return !(this.getShip() != null && this.getWorld() != null);
	}
	
	 /**
     * Check whether or not this bullet belongs to a proper ship.
     * 
     * @return True if and only if 
     * 			-This bullet can indeed have the ship to which it belongs as its ship.
     * 			-This ship is either not effective or has this bullet as one of its bullets.
     * 			| result ==
     * 			|			(canHaveAsShip(getShip()) &&
     * 			|			((getShip() == null) || getShip().hasAsBullet(this)))
     */
	@Raw
    public boolean hasProperShip(){
        return ( (canHaveAsShip(getShip())) && 
        		  ((getShip() == null) || getShip().hasBullet(this)) );
    }
    
    /**
     * Check whether this bullet can belong to the given ship
     * 
     * @param ship
     * 		  The ship to investigate
     * @return True if and only if 
     * 		   -The given ship is either not effective or it can have this bullet as one of its bullets.
     * 			| result == ((ship == null) || ship.canHaveAsBullet(this))
     */
    @Raw
    public boolean canHaveAsShip(Ship ship){
        return ((ship == null) || ship.canHaveAsBullet(this));
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
	 * 
	 * @param ship
	 * 		  The ship that we try to set as owner
	 * 
	 * @throws IllegalPositionException 
	 * 
     * @pre If the given ship is not effective and this bullet references an effective ship,
     * 		that ship may not reference this bullet as one of its bullets.
     * 		| if ((ship == null) && (getShip()!=null))
     * 		|		then !getShip().hasAsBullet(this)
     * 
	 * @pre   If the given ship is effective, it must  have this bullet as one of its bullets.
	 *        |if (ship != null)
     * 		  |		then ship.hasBullet(this)
	 * 
	 * @post This bullet gets the given ship as its owner
	 * 		 |new.getShip() = ship;
	 * 			
	 */
	public void setShip(Ship ship) {
        assert((ship != null) || (getShip() == null) || (!getShip().hasBullet(this)));
		assert((ship == null) || (ship.hasBullet(this)));
        this.ship = ship;
        //If the ship is effective, the location of this bullet is changed to the center of the ship.
//        if (ship!=null)
////        	this.setPosition(ship.getxPosition(),ship.getyPosition());
	}
	
	//setWorld is handled within entity class.
	
	// ---------------------- Bounces 
	
	
	/**
	 * Return the munber of times this bullet can still bounce of a boundary without 'dying'.
	 */
	public int getBouncesLeft() {
		return this.bounces_left;
	}
	
	
	/**
	 * This will decrement the bounces that a bullet can still make each time a bullet touches a boundary.
	 * It then checks to see if this amount has reached zero. If it has, the bullet is terminated.
	 * 
	 * @post The amount of bounces_left is decremented by 1
	 * 		 |new.bounces_left = old.bounces_left - 1;
	 * @post If the new amount equals zero (or lower in case something goes wrong in a corner)
	 * 		 |if (this.getBouncesLeft()<= 0) 
	 * 		 | 		then  this.isTerminated() == true;
	 */
	public void decrementBouncesLeft(){
		bounces_left = bounces_left - 1;
		if (this.getBouncesLeft()<= 0)
	          this.terminate();  
	}
	
	/**
	 * Return the maximum number of times this bullet can bounce of the boundaries of a world.
	 */
	public int getMaxBounces() {
		return this.Max_Bounces;
	}
	
	/**
	 * Set the maximum number of bounces of this bullet to a given value.
	 * @post If the given maximum number is negative, the maximum number is set to 3.
	 * 			@see implementation
	 * @post Otherwise, the new maximum number equals the given number.
	 * 		|new.getMaxBounces == bounces
	 */
	public void setMaxBounces(int bounces) {
		if (bounces<0)
			this.Max_Bounces=3;
		else
			this.Max_Bounces=bounces;
	}
	

	
	/**
	 * A variable registering the maximum number of times this bullet can bounce of the boundaries of a world.
	 */
	private int Max_Bounces = 3;
	
	
	/**
	 * A variable recording the number of times this bullet has bounced off the boundaries of a world.
	 * It is initialised as the maximum number of times a bullet can bounce.
	 */
	private int bounces_left = Max_Bounces;
	
	
	
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
