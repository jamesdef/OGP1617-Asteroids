package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;

import java.util.HashSet;
import java.util.Set;


/**
 * A class for dealing with ships, which are a kind of entity in space. These have a certain position, velocity, radius, speed and orientation.
 * The ship has a thrusther with which it can accelerate. The ship also has a mass and a certain density.
 * 
 *@invar The invariants of the superclass 'Entity' are described there.
 *
 *@invar Each ship has proper bullets as its belongings.
 *		 |hasProperBullets()
 *   
 * @version 2.0     
 * @author James Defauw & Michiel De Koninck

 */

public class Ship extends Entity {

	//-------------------------------   Constructors: #2 --------------------------------------------
	/**
	 * Initialize this new ship with given position,radius, speed and mass.
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this ship, according to the x-axis. 
	 *         Expressed in km.
	 *         
	 * @param  yPosition
	 *         The position of this ship,according to the y-axis.
	 *         Expressed in km.
	 *         
	 * @param  radius
	 * 		   The radius of this new ship.
	 * 		   Expressed in km.	
	 * 
	 * @param  xVelocity 
	 *         The velocity of this vessel, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the vessel, in the y-direction. 
	 * 		   Expressed in km/s.
	 *         
	 * @param  orientation
	 * 		   The orientation of this vessel, i.e., it's direction.
	 * 		   Expressed in radians.    
	 * 
	 * @param mass
	 * 		  The mass of this vessel.
	 * 
	 * @effect The given mass is set as the mass of this new ship.
	 * 		   |setMass(mass);
	 * 
	 * @effect The given parameters are set as the properties of the new ship.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *	       |setOrientation(orientation);
	 *
	 *
	 * 
	 *@note    Any new ship initialized with this constructor (via superclass Entity)
	 * 		   will satisfy all its class invariants. The setters will see to this in their implementation.
	 */

	// Position X and Y are described seperatly, this proves to be the easiest to work with. Same goes for velocity.
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation,double mass) 
							throws IllegalPositionException, IllegalRadiusException{
		super(xPosition, yPosition, xVelocity, yVelocity, radius, orientation);
		setMass(mass);
	}
	/**
	 * Initialize this new ship with the parameters set to their default values.
	 * 
	 * @effect 	This new ship is initialized in the center of the grid: (0,0)
	 * 			It's radius will be set to it's lowest possible value.
	 * 			It's velocity will be set to it's minimal value: 0. The ship will not be moving.
	 * 			It's orientation is to the right (Minimum value=0), i.e.: it has an angle of 0 radians to the x-axis.
	 * 			|this.xPosition = 0.0
	 * 			|this.yPosition = 0.0
	 * 			|this.getxVelocity = Min_Velocity
	 * 			|this.getyVelocity = Max_Velocity
	 * 			|this.getOrientation = Min_Orientation
	 * 			|this.getRadius = Min_Radius
	 * i.e. :   |this(0.0,0.0,Min_Velocity,Max_Velocity,Min_Radius,Min_Orientation);
	 * 
	 * @note We know that the exceptions can never be thorwn in this default case, but JAVA makes us throw them anyway.
	 */
    public Ship() throws IllegalPositionException, IllegalRadiusException{
		this(0.0,0.0,Min_Velocity,Min_Velocity,Min_Radius,Min_Orientation,0.0);
	}

    
    //---------------------------- Termination -----------------
    
    
    /**
     * Terminates this ship and all of it's property.
     * It does this by first deleting it's on board bullets and than terminating the ship itself.
     * 
     * @effect All bullets are first removed and then the ship itself is terminated.
     * 		| new.getNbOfBullets  == 0 
	 * 		| new.isTerminated() == true
	 * 
     */
    public void terminate(){
    	for (Bullet bullet : bullets){
    		removeBullet(bullet);
    	}
    	//We can now safely terminate the empty ship as an entity.
    	super.terminate();
    }
    
	// -----------------------  VARIABLES (DEFAULTS & FINAL) --------

	/**
	 * Variable registering the radius of this Ship.
	 */
	private double radius = Min_Radius;

	/**
	 * Variable registering the minimum allowed Radius.
	 * The minimum radius may change in the future. 
	 * But it will always remain the same for all Ships.
	 *
	 */
	// Dit is een waarde die je moet overschrijven maar weet nog niet hoe voorlopig.
	//@ Override 
	private static double Min_Radius = 10.0;
	
    /**
     * Variable registering the density of this ship.
     * @Override
     */
    private double density = Min_Density;
  
    /**
     * Variable registering the minimum allowed density.
     * @Override
     */
    
    private static final double Min_Density = 1.42*(Math.pow(10, 12));
    
    /**
     * Variable registering the Minimum allowed mass.
     * @Override
     */
    private final double Min_Mass = Min_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
    
    /**
     * Variable registering the mass of this ship.
     *
     */
    private double mass = Min_Mass;
   
   // ------------------------------
    
    
    
	/**
	 * Total Programming:
	 * Sets the mass of this entity to the given value.
	 * 
	 * @param mass
	 * 		 The given mass to which we want to set the mass of this entity.
	 * @post if the given mass is valid, this mass is set as the new mass.
	 * 		|if (isValidMass(double mass))
	 * 		|	then new.mass == mass
	 * @post if the given mass does not 
	 */
	public void setMass(double mass){
		if(!isValidMass(mass)){
	        mass = 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
		}
	    this.mass = mass;
	}
	
	/**
	 * Checks whether the given mass is valid.
	 * 
	 * @param mass
	 * 		  The mass to check
	 * @return Whether the mass is valid for a ship to have.
	 * 		   |result == (mass >= 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getMassDensity())
	 */
	public boolean isValidMass(double mass){
        return (mass >= 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity());
	}
	
	/**
	 * Returns the mass of this ship.
	 * @return the mass of this ship.
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
	
	public void setDensity(double density){
		if (!isValidDensity(density)){
			density = Min_Density;
		}
		this.density = density;
	}
	
	public boolean isValidDensity(double density){
		return (density >= Min_Density);
	}
    
	// ------------------------   The inspectors -------------------------------------------------------


    
    //MASS - Total programming
    
    
    /** 
     * This returns the sum of all the masses of the bullets' on this ship
     */
    
    public double getMassOfBullets(){
    	double bulletMass=0;
    	
		for (Bullet bullet: this.getBullets())
			bulletMass =+ bullet.getMass();
		return bulletMass;
    }
    
    /**
     * This method returns the total mass of this ship. 
     * 
     * This mass is equal to the mass of the ship itself plus the mass of
     * objects carried by the ship
     * 
     * @return  The mass of the ship itself plus the mass of
     * 			objects carried by the ship
     * 			|return this.getMass() + GetMassOfAllBulletsOwnedByThisShip;
     */
	public double getTotalMass(){
		return (this.getMass() + getMassOfBullets());
	}
	
	
	// ------------------------------------ SETTERS --------------------------
	
 
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
    public void setMin_Radius(double Lower_Bound){
    	if (Lower_Bound > 0){
    		Ship.Min_Radius = Lower_Bound;
    	}
    	else{
    		throw new IllegalArgumentException(Double.toString(Lower_Bound));
    	}
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

	
	
	//-------- MOVING, TURNING AND ACCELARATING-----
	
	/**
	 * The variable thrust defines whether the thrust of this ship is enabled or not.
	 * It is initialised as being disabled.
	 */
	private boolean thrust = false;
	
	/**
	 * This method can enable the thrust for this ship.
	 */
	public void thrustOn(){
		this.thrust = true;
	}
	
	/**
	 * This method can disable the thrust for this ship.
	 */
	public void thrustOff(){
		this.thrust = false;
	}
	
	/**
	 * This method returns whether the thruster of this ship is enabled or not.
	 * @return Whether the thruster is enabled or not.
	 * 		   True when it is enabled, false otherwise.
	 */
	@Basic
	public Boolean getthrustState(){
		return thrust;
	}
	
	
	/**
	 * This method returns the acceleration that this ship would get when the thruster is enabled.
	 * It is calculated using Newton's second law of motion.
	 * 
	 * @return The acceleration of this ship.
	 * 		   |return (force/getTotalMass())
	 */
	public double getPossibleAcceleration(){
		return force/getTotalMass();
	}
	
	
	/**
	 * A variable defining the force that an active thruster can exert on a ship.
	 */
	private final double force = 1.1E21;
	

	
	/**
	 *  Raises the velocity of the ship if the thruster is enabled.
	 *  This raising is based on a given duration, the possible acceleration and the ship's orientation.
	 * 
	 * @param The duration during which the acceleration happens.
	 * 
	 * @post If the possible acceleration is negative or the thruster is disable;
	 * 		 The velocity is left untouched
	 * 		 |if (acceleration < 0 || getThrustState() == false)
	 * 		 | 		then new.getxVelocity() = getxVelocity()
	 * 	     | 		then new.getyVelocity() = getyVelocity()
	 *
	 * @effect The velocity is changed, depending on the duration, the possible acceleration of this ship and the orientation.
	 * 	       This is only the case if the calculated new velocity is legal.
	 * 		   The method setVelocity makes sure that this is the case.
	 *		 |NewxVelocity = this.getxVelocity() + a*(Math.cos(this.getOrientation()))*duration;
	 *	     |NewyVelocity = this.getyVelocity() + a*(Math.sin(this.getOrientation()))*duration;
	 * 		  |this.setVelocity(NewxVelocity, NewyVelocity);
	 */
	public void accelerate(double duration){
		if(getthrustState() == true){
			//If the acceleration is negative, then we leave the velocity untouched.
			double a = Math.max(0, getPossibleAcceleration());
			double NewxVelocity = this.getxVelocity() + a*(Math.cos(this.getOrientation()))*duration;
			double NewyVelocity = this.getyVelocity() + a*(Math.sin(this.getOrientation()))*duration;
			this.setVelocity(NewxVelocity, NewyVelocity);	
			}	
	}

	
	/**
	 * Move this ship and all it's on-board belongings with it.
	 * 
	 * @throws IllegalDurationException 
	 * @throws IllegalPositionException 
	 * 
	 * @effect The ship itself is moved, as well as all the entities that are in it.
	 */
	@Override
	public void move(double duration) throws IllegalPositionException, IllegalDurationException{
		super.move(duration);
		
		// Je zou misschien gewoon de bullets kunnen plaatsen op de plaats waar het schip staat wanneer het klaar is met bewegen.
		// Immers moeten de bullets toch niks doen tijdens de beweging? (maar is dat dan niet verkeerd omdat de bullets blijven staan op de oude coordinaten
		// tijdens het bewegen van het schip?
//	     for (Bullet bullet: this.bullets) {
//	            bullet.setPosition(this.getPosition());
	}

	/**
	 *  Adjust the orientation of the ship by a given angle
	 * 
	 * @param angle
	 * 		  The angle to add to the orientation of the ship
	 * 
	 * @post  The scaledangle is calculated by a seperate method
	 * 		  | scaledAngle = scaleAngle(this.getOrientation() + angle)
	 * 
	 * @post   The new orientation is equal to the calculated scaled angle.
	 * 		  |new.getOrientation() == scaledAngle
	 * 
	 * @effect The given angle is first added to the orientation and then scaled.
	 * 		   The new orientation is then asserted to be within 0-2PI range in the setOrientation() method.
	 * 		   |this.setOrientation(scaledAngle);
	 */
	public void turn(double angle){
		double newAngle = this.getOrientation() + angle;
		double ScaledAngle = scaleangle(newAngle);
		this.setOrientation(ScaledAngle);
	}

	/**
	 *  Scales the given angle so that it is within 0<= angle < 2*PI
	 * 
	 * @param angle
	 * 		  The angle to scale 
	 * @return returns the angle scaled to fit the boundaries of the orientation.
	 *         | result = angle % getMax_Orientation;
	 */
	public double scaleangle(double angle){
		double ScaledAngle = angle % Max_Orientation;
		return ScaledAngle;
	}
	
	
	
	
	
	
	//BULLETS --------------------------
	
	/**
	 * A variable registering the bullets owned by this ship.
	 */
    private Set<Bullet> bullets = new HashSet<Bullet>();
    
    
    /**
	 * Return the bullets owned by this ship.
	 * @return the bullets owned by this ship
	 */ 
	public Set<Bullet> getBullets() {
		return this.bullets;	
	}

	/**
	 * Returns the number of bullets this ship contains.
	 * @return this.getBullets().size()
	 */
	public int getNbOfBullets(){
		return this.getBullets().size();
	}
	
	/**
	 * Check whether this ship has the bullet as one of its bullets.
	 * @param bullet
	 * 		  The bullet to investigate
	 * @return True if and only if the bullet belongs to this ship.
	 * 		   |result == bullets.contains(bullet);
	 */
	public boolean hasBullet(Bullet bullet){
		return getBullets().contains(bullet);
	}
	
    /**
     * Check whether this ship can have the given bullet
     * as one of its bullets.
     *
     * @param  bullet
     *         The bullet to check.
     * @return True if and only if all folowing statements hold
     * 		   -The given bullet is effective
     *         -This ship is not terminated or the bullet is terminated.
     *         -This ship does not already have the bullet as one of it's bullets
     *         -This ships' radius is bigger than that of the bullet.
     *         | result == ( (bullet != null) 
     *         |       && ((!this.isTerminated()) || (bullet.isTerminated()))
     *         |			 && (!this.hasBullet(bullet)) 
     *         |					&& (bullet.getRadius()<this.getRadius())  )
    }
     */
    public boolean canHaveAsBullet(Bullet bullet) {
    	  return ( (bullet != null) 
    			       && ((!this.isTerminated()) || (bullet.isTerminated()))
    			     		 && (!this.hasBullet(bullet)) 
    			     				&& (bullet.getRadius()<this.getRadius())  );		  
    }
	
    /**
     * Check whether all the bullets attached to this ship are 'legal'.
     * 
     * @return True if and only if this ship can have each of its bullets
     * 		   as one of its bullets. Each of the bullets also has to reference the ship
     * 		   to which it belongs.
     * 		   |for bullet in getBullets():
     * 		   |    result == ( canHaveAsBullet(bullet) && bullet.getShip() == this )	   
     */
    public boolean hasProperBullets(){
    	for (Bullet bullet : getBullets()) {
    		if (!canHaveAsBullet(bullet)){
    			return false;
    		}
    		if (bullet.getShip() != this){
    			return false;
    		}
    	}
    	//Otherwise all requirements hold and we return true
    	return true;
    }
    
	
	/**
	
	/**
	 * Adds a bullet to the bullets loaded on the ship
	 * @throws IllegalRadiusException 
	 * @throws IllegalPositionException 
	 */
	public void loadBullet() throws IllegalPositionException, IllegalRadiusException{
		Bullet bullet = new Bullet(this.getxPosition(), this.getyPosition(), this.getxVelocity(), this.getyVelocity(),
												Bullet.Min_Radius, this.getOrientation());
		this.getBullets().add(bullet);
	}
	
	/**
	 * Adds a specified amount of bullets to the ship
	 * 
	 * @param numberOfBullets
	 * @throws IllegalRadiusException 
	 * @throws IllegalPositionException 
	 */
	public void addMultipleBullets(int numberOfBullets) throws IllegalPositionException, IllegalRadiusException{
		for(int i=0; i<numberOfBullets; i++){
            loadBullet();
       }
		
		
		
	}
	
	/**
	 * Removes a specified bullet from the ships' bullets.
	 * 
	 * @param bullet
	 * 		  The bullet to remove
	 * @post  If this ship does not have the given bullet as one of its bullets;
	 * 		  nothing is changed.
	 * @post  This ship no longer has the given bullet as one of its bullets.
	 * 		  | !new.hasBullet(bullet)
	 * @post  If this ship has the given bullet as one of its bullets, 
	 * 			the given bullet no longer references any ship.
	 * 		  | if (hasBullet(bullet){
	 * 		  |       (bullet.getShip() == null)
	 */
	public void removeBullet(Bullet bullet){
		if (hasBullet(bullet)){
			bullets.remove(bullet);
			bullet.setShip(null);
		}
	}
	
	/**
	 * Fires a bullet
	 */
	public void fireBullet(Bullet bullet){
		//if not in world, can't fire
		if(belongsToWorld()){
			//bullet is fired
			if(canPlaceBullet(bullet)){
				//fire bullet
				
				double Bulletspeed = 10;
				double xSpeed = Bulletspeed*Math.cos(this.getOrientation());
				double ySpeed = Bulletspeed*Math.sin(this.getOrientation());
				
				//remove bullet from ship qs it's fired
				removeBullet(bullet);
				bullet.setVelocity(xSpeed, ySpeed);
				
			} else {
				
			}
		}
		
	}

	
	/**
	 * Checks whether a bullet can initially be placed before actually being fired
	 * 
	 */
	public boolean canPlaceBullet(Bullet bullet){
		if(belongsToWorld()){
		//if collides with other entity upon placement
		double bulletXPos = this.getxPosition() + (this.getRadius() + bullet.getRadius())*Math.cos(this.getOrientation());
		double bulletYPos=  this.getyPosition() + (this.getRadius() + bullet.getRadius())*Math.sin(this.getOrientation());
	
		try {
			bullet.setPosition(bulletXPos, bulletYPos);
		} catch (IllegalPositionException e) {
			return false;
		}
		
			if(getWorld().overlaps(bullet)){
				return false;
			}
			
		}
		return true;
	}
	
	public boolean belongsToWorld(){
		 if (getWorld() != null) return true;
		 return false;
		
	}
	
}
