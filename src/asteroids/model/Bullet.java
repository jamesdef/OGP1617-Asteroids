package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.model.exceptions.*;

//TODO if a bullet hits it's own ship, it is reloaded but its bounces
// are not forgotten, where shall we say this?

/**
 * A class for dealing with bullets, which are a kind of entity in space. 
 * These have a certain position, velocity, radius, mass and density.
 * A bullet can have an owner: a ship or a world. 
 * If it has a ship as its owner, it can not have a world as its owner.
 * If a bullet is fired, it has a source (a ship that fired it); unless this ship has been destroyed in the meanwhile.
 * A bullet can only bounce of a boundary a given number of times.
 *
 * @invar 	Each bullet must have a proper owner at all times.
 *		  	|hasProperOwner()
 *
 * @invar 	Each bullet should have a proper ship, if this is its owner.
 * 		  	|hasProperShip()
 *
 * @invar 	The radius of each bullet must be a valid value.
 * 			|isValidRadius(getRadius())
 *
 * @invar   The mass of a bullet must be valid.
 * 		    |this.getMass() > 0
 * 
 * @invar   The density of a ship must be valid.
 * 		    |isValidDensity(this.getDensity)
 * 
 * @version 2.0     
 * @author James Defauw & Michiel De Koninck

 */
public class Bullet extends Entity {
	
	
	
	/**
	 * Initialize this new bullet with given position,radius, speed.
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this bullet, according to the x-axis. 
	 *         Expressed in km.
	 *         
	 * @param  yPosition
	 *         The position of this bullet,according to the y-axis.
	 *         Expressed in km.
	 *         
	 * @param  radius
	 * 		   The radius of this new bullet.
	 * 		   Expressed in km.	
	 * 
	 * @param  xVelocity 
	 *         The velocity of this bullet, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the bullet, in the y-direction. 
	 * 		   Expressed in km/s.
	 *          
	 * @effect The given parameters are set as the properties of the new bullet.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *		   |setBulletMass(new.getRadius());
	 *
	 */
	public Bullet(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius) throws IllegalPositionException, IllegalRadiusException{
		super(xPosition, yPosition, xVelocity, yVelocity, radius);
		setBulletMass(getRadius());
		
	}
	
	
// -------------- Termination -------------------------
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

        // The bullet is terminated as an entity, now that all other links have been undone.
        super.terminate();
    }
	
// ---------------- Getters----------------------------------------
		
		/**
		 * Returns the ship to which this bullet belongs.
		 */
		@Basic
		public Ship getShip(){
			return this.ship;
		}
		
		/**
		 * Returns the density of this bullet.
		 */
		@Basic
		public double getDensity(){
			return this.density;
		}
		
		/**
		 * Returns the minimum radius for bullets.
		 */
		@Basic
		public static double getMinRadius(){
			return Bullet.min_Radius;
		}


//-------------------- Ownership; Associations ------------------------
    
	/**
	 * Check whether this bullet has a proper owner. 
	 * Meaning that it can not belong to both a world and a ship.
	 * @return True if and only if this bullet has only one owner.
	 * 		   | result !((this.getShip() != null && this.getWorld() != null)
	 */
    @Raw
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
    @Raw
	public void setShip(Ship ship){
        assert((ship != null) || (getShip() == null) || (!getShip().hasBullet(this)));
		assert((ship == null) || (ship.hasBullet(this)));
        this.ship = ship;
        //If the ship is effective, the location of this bullet is changed to the center of the ship.
        if (ship!=null)
			try {
				this.setPosition(ship.getxPosition(),ship.getyPosition());
			} catch (IllegalPositionException e) {
				// Zal niet voorkomen; de positie van een schip moet op elk moment legaal zijn.
				// Java verplicht ons enkel deze try/Catch in te voeren.
			}
	}
	
	
	//--------------- Source----------------------
	/**
     * Returns the source that fired this bullet. 
     * If it exists; this will always be a ship.
     */
    @Basic
    public Ship getSource() {
        return this.source;
    }
    

 // ----------------MASS ----------------------
 	
 	/**
 	 * This method sets the mass of this bullet depending on it's size (=defined by radius)
 	 * 
 	 * @param radius
 	 * 		  The radius of this bullet.
 	 * @post The new mass of this bullet now equals the value calculated using the formula with the given radius.
 	 * 		 |new.mass == default_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
 	 */
 	private void setBulletMass(double radius){
 		this.mass = default_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
 	}
     

    /**
	 * Set the source of this bullet to a given ship.
	 * 
	 * @param	source
	 * 			The ship that fired this bullet.
	 * @post	The source of this bullet is the given ship.
	 * 			| new.getSource() == source
     * @throws IllegalShipException 
	 * 			If this bullet 
	 * 				-already belongs to a world
	 * 				-already is loaded/fired by a ship, that is not this ship. 
	 * 					(It can be this ship if the bullet was loaded upon this ship once again
	 * 					after previously being fired by it).
	 * 		    |(this.getWorld()!=null||this.getShip()!=null||this.getSource()!=null && this.getSource() != source)
	 */
	public void setSource(Ship source) throws  IllegalShipException {
		if (this.getWorld()!=null||this.getShip()!=null||(this.getSource()!=null && this.getSource() != source))
			throw new IllegalShipException(source);
		if (source==null)
			this.source=null;
		else
			this.source = source;
	}
    
	
// ----------------- RADIUS ---------------------------------------------
	
	 /**
     * This method makes it possible for the user to change the lower bound
     * imposed upon bullets.
     * 
     * @param lowerbound
     * 		  The new minimum radius.
     * 
     * @post The new universal lower bound for the radius is equal to the given value.
     * 		 |new.getMinRadius() == Lower_Bound
     * 
     * @throws IllegalRadiusException 
     * 		   The given l is not valid.
     * 		   | !(Lower_bound > 0)
     */
	@Raw
    public static void setMin_Radius(double lowerbound) throws IllegalRadiusException{
    	if (isValidMinimumRadius(lowerbound)){
    		Bullet.min_Radius = lowerbound;
    	}
    	else{
    		throw new IllegalRadiusException(lowerbound);
    	}
    }
    
	/**
	 * Check whether the given minimum radius is a valid limit.
	 * 
	 * @param Min_Radius
	 * 		  The minimum radius to check.
	 * @return Whether the minimum radius is positive or not.
	 * 			| result == (min_Radius > 0)
	 */
    @Raw
	public static boolean isValidMinimumRadius(double min_Radius){
		return (min_Radius > 0);
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
	 * 		   | radius >= getMinRadius();
	 */
    @Raw @Override
	public boolean isValidRadius(double radius){
		return (radius >= Bullet.getMinRadius() && (!Double.isNaN(radius) && radius != Double.POSITIVE_INFINITY));
	}
	
	
// ---------------------- Bounces -----------------------------------------------------
	
	
	/**
	 * Return the munber of times this bullet can still bounce of a boundary without 'dying'.
	 */
    @Basic
	public int getBouncesLeft() {
		return this.bounces_left;
	}
	
	
	/**
	 * This will decrement the bounces that a bullet can still 
	 * make each time a bullet touches a boundary.
	 * 
	 * @post The amount of bounces_left is decremented by 1
	 * 		 |new.bounces_left = old.bounces_left - 1;
	 */
	public void decrementBouncesLeft(){
		this.bounces_left = bounces_left-1;
	}
	
	/**
	 * Return the maximum number of times this bullet can bounce of the boundaries of a world.
	 */
	@Basic
	public int getMaxBounces() {
		return this.max_Bounces;
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
			this.max_Bounces=3;
		else
			this.max_Bounces=bounces;
	}
	

// ---------------------  Initialising Variables & Defaults -------------------------------
	
	
	/**
	 * A variable registering the maximum number of times this bullet can bounce of the boundaries of a world.
	 */
	private int max_Bounces = 3;
	
	/**
	 * A variable recording the number of times this bullet has bounced off the boundaries of a world.
	 * It is initialised as the maximum number of times a bullet can bounce.
	 */
	private int bounces_left = max_Bounces;
	
	/**
	 * Variable registering the minimum allowed Radius.
	 * The minimum radius may change in the future. 
	 * But it will always remain the same for all Bullets.
	 */
	private static double min_Radius = 1.0;
	
	/**
	 * Field initialising the existence of ship.
	 * Initialised to a value of null.
	 */
	private Ship ship = null;


    /**
     * Variable referencing the source ship (that fired the bullet) of the bullet.
     * In default; set to null.
     */
    private Ship source = null;
	
	/**
	 * Variable registering the default density of a bullet.
	 */
	protected final static double default_Density = 7.8*(Math.pow(10.0, 12.0));
	
	/**
	 * Variable registering the density of this bullet.
	 */
	private double density = default_Density;
	
	/**
	 * Variable registering the Default_Mass of a bullet
	 */
	protected final static double default_Mass = default_Density*(4.0/3.0)*Math.PI*(Math.pow(min_Radius, 3.0));
	
	
}
