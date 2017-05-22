package asteroids.model;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalShipException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class for dealing with ships, which are a kind of entity in space. 
 * These have a certain position, velocity, radius, speed and orientation.
 * The ship has a thrusther with which it can accelerate. The ship also has a mass and a certain density.
 * A ship can have bullets, which it can fire. A ship can collide with other things in it's world.
 * 
 * @invar 	The invariants of the superclass 'Entity' are described there.
 * 			They ofcourse hold for this subclass.
 *
 * @invar 	Each ship has proper bullets as its belongings.
 *		 	|hasProperBullets()
 *
 * TODO voorbeeld van liskov: klasse invariant verstrenging.
 * @invar 	The radius of each ship must be a valid value.
 * 			|isValidRadius(getRadius())
 *
 * @invar   The mass of a ship must be valid.
 * 		    |isValidMass(this.getEntityMass)
 * 
 * @invar   The density of a ship must be valid.
 * 		    |isValidDensity(this.getDensity)
 * 
 * @invar	The orientation of the ship must be a valid value.
* 			|isValidOrientation(getOrientation())
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
	 * 		  Expressed in kg.
	 * 
	 * @effect The given mass is set as the mass of this new ship.
	 * 		   |setMass(mass);
	 * 
	 * @effect The given parameters are set as the properties of the new ship.
	 * 		   |super(xPosition, yPosition, xVelocity, yVelocity, radius, orientation);
	 *
	 *@note    Any new ship initialized with this constructor (via superclass Entity)
	 * 		   will satisfy all its class invariants. The setters will see to this in their implementation.
	 */

	// Position X and Y are described seperatly, this proves to be the easiest to work with. Same goes for velocity.
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation,double mass) 
							throws IllegalPositionException, IllegalRadiusException{
		super(xPosition, yPosition, xVelocity, yVelocity, radius);
		setOrientation( orientation);
		setShipMass(mass);
	}
	/**
	 * Initialize this new ship with the parameters set to their default values.
	 * 
	 * @effect 	This new ship is initialized in the center of the grid: (0,0)
	 * 			It's radius will be set to it's lowest possible value.
	 * 			It's velocity will be set to it's minimal value: 0. The ship will not be moving.
	 * 			It's orientation is to the right (Minimum value=0), i.e.: it has an angle of 0 radians to the x-axis.
	 *			It's mass is set to the default value (being the mass for the smallest ship with the lowest density).
	 * 			|this.getxPosition() = 0.0
	 * 			|this.getyPosition() = 0.0
	 * 			|this.getxVelocity() = getMinVelocity()
	 * 			|this.getyVelocity() = getMinVelocity()
	 * 			|this.getOrientation() = getMinOrientation()
	 * 			|this.getRadius() = getMinRadius()
	 * 			|this.getMass() = getDefaultMass()
	 * i.e. :  |this(0.0,0.0,getMinVelocity(),getMinVelocity(),getMinRadius(),getMinOrientation(),getDefaultMass())
	 */
	@Raw
    public Ship() throws IllegalPositionException, IllegalRadiusException{
    	this(0.0,0.0,getMinVelocity(),getMinVelocity(),getMinRadius(),getMinOrientation(),getDefaultMass());	
	}

    
//---------------------------- Termination -----------------
    
    
    /**
     * Terminates this ship and all of it's property.
     * It does this by first deleting it's on board bullets and than terminating the ship itself.
     * 
     * @effect All bullets are first removed and then the ship itself is terminated.
     * 			@see implementation
     * @post After the termination, this ship has no more bullets, and is terminated.
     *		| new.getNbOfBullets  == 0 
	 * 		| new.isTerminated() == true
	 * 
     */
    public void terminate(){

    	if (this.getNbOfBullets() != 0) {
    		Set<Bullet> toremovebullets = new HashSet<>();
    		toremovebullets.addAll(bullets);
    	for (Bullet bullet : toremovebullets){
    		removeBullet(bullet);
    	}}
    	//We can now safely terminate the empty ship as an entity.
    	super.terminate();
    }

// ------------------------------ MASS & DENSITY-------------
   
	/**
	 * Sets the mass of this ship to the given value.
	 * 
	 * @param mass
	 * 		 The given mass to which we want to set the mass of this ship.
	 * @post if the given mass is valid, this mass is set as the new mass.
	 * 		|if (isValidMass(double mass))
	 * 		|	then new.getMass() == mass
	 * @post else: the given mass is not valid; the mass is set to a default value:
	 * 		@ see implementation
	 */
    @Raw 
	public void setShipMass(double mass){
		if(!isValidMass(mass)){
			mass = 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3.0) * this.getDensity();
		}
	    super.setMass(mass);
	}
	
	/**
	 * Checks whether the given mass is valid.
	 * 
	 * @param mass
	 * 		  The mass to check
	 * @return Whether the mass is valid for a ship to have.
	 * 		   For this it has to be bigger than the minimum and has to be a number.
	 * 		   |result == ((mass >= 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getMassDensity())
	 		   |  && !Double.isNaN(mass) )
	 */
	@Raw
	public boolean isValidMass(double mass){
        return ((mass >= 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3.0) * this.getDensity()) 
        		&& !Double.isNaN(mass) );
	}
	
	/**
	 * Returns the density of this ship.
	 */
	@Basic
	public double getDensity(){
		return this.density;
	}
	
	/**
	 * Sets the density of this ship to the given value, if this is valid.
	 * @param density
	 * 		  The density to change to.
	 * @post If the given value is valid, it is changed.
	 * 		 |if isValidDensity(density)
	 * 		 |	then new.getDensity() == density
	 * @post If the given value is not valid, the density is set to the minimum density.
	 * 		|if (!isValidDensity(density)){
	 *		|	then new.density = Min_Density;
	 */
	public void setDensity(double density){
		if (!isValidDensity(density)){
			density = min_Density;
		}
		this.density = density;
	}
	
	/**
	 * Returns whether the given density is valid.
	 * 
	 * @param density
	 * 		  The density to check.
	 * @return True if the given density is greater than or equal to the Minimum Density
	 * 		   |density >= Min_Density
	 */
	public boolean isValidDensity(double density){
		return (density >= min_Density);
	}
        
    //MASS - Total programming<
	
    /** 
     * This method returns the sum of all the masses of the bullets on this ship.
     * 
     * @return The sum of all the masses of the bullets on this ship.
     * 		   It does this by adding the mass of all bullets together
     * 			|@see implementation
     * 		   
     */
    public double getMassOfBullets(){
    	double bulletMass = 0;
    	
    	for (Bullet bullet: this.getBullets()){
			bulletMass =+ bullet.getMass();
		}
		
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
	
	/**
	 * Return the minimum mass a ship can have.
	 */
	@Basic
	public double getMinMass(){
		return this.min_Mass;
	}
	
	/**
	 * Return the default mass of a ship.
	 * 
	 * @return 	The default mass is actually not the same for all ships. 
	 * 			However here we have implemented a default mass as that of a ship with minimal radius.
	 * 			@see implementation
	 */
	@Immutable 
	private static double getDefaultMass() {
		return (4.0 / 3.0) * Math.PI * Math.pow(getMinRadius(), 3) * Ship.getMinDensity();
	}
	
	/**
	 * Returns the minimum density for this ship.
	 */
	@Basic @Immutable
	public static double getMinDensity(){
		return min_Density;
	}

	
//--------------------------------------Orientation-----------------------
	
	/**
	 *  Return the orientation of this ship.
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;	
	}	
	
	/**
	 *  Sets the orientation to the given angle, if this is a valid angle.
	 * 
	 * @param orientation
	 * 		  The new, given orientation of the ship.
	 * @pre The given orientation must be a valid one.
	 * 		|isValidOrientation(orientation)
	 * @post The orientation of the ship is now changed to the given value
	 *		|new.getOrientation()== orientation
	 */
	@Raw
	public void setOrientation(double orientation){
		System.out.println("orientation" + orientation);
		System.out.println(isValidOrientation(orientation));
		assert isValidOrientation(orientation);
		this.orientation = orientation;	
	}

	/**
	 *  Check whether the given orientaton is a valid value.
	 * 
	 * @param orientations
	 * 		  The orientation of which we need to check whether it is legal.
	 * 
	 * @return True if and only if the given orientation is within the boundaries opposed upon orientation. (And has to be a number)
	 * 		   |result == (getMin_Orientation() <= orientation) && (orientation < getMax_Orientation()) && !Double.isNaN(orientation)
	 * 
	 */
	public static boolean isValidOrientation(double orientation){
		return ((getMinOrientation() <= (orientation)) && (orientation <= getMaxOrientation()) && (!Double.isNaN(orientation)));	
	}

	/**
	 * Returns the minimum orientation for this ship.
	 * @return the minimum orientation for this ship.
	 */
	@Basic @Immutable
	public static double getMinOrientation(){
		return min_Orientation;
	}

	/**
	 * Returns the maximium orientation for this ship.
	 * @return the maximum orientation for this ship.
	 */
	@Basic @Immutable
	public static double getMaxOrientation(){
		return max_Orientation;
	}	
	
	
	
// ------------------------------------ Radius --------------------------
	
 
	 /**
     * This method makes it possible for the user to change the lower bound
     * imposed upon ships, for all ships.
     * 
     * @param lowerbound
     * 		  The new minimum radius.
     * 
     * @post The new universal lower bound for the radius is equal to the given value.
     * 		 |new.Min_Radius == Lower_Bound
     * 
     * @throws IllegalRadiusException 
     * 		   The given lowerbound for the radius is not valid.
     * 		   | !(Lower_bound > 0)
     */
    public void setMin_Radius(double lowerbound) throws IllegalRadiusException{
    	if (lowerbound > 0){
    		Ship.min_Radius = lowerbound;
    	}
    	else{
    		throw new IllegalRadiusException(lowerbound);
    	}
    }
    
	/**
	 * Return the minimum radius a ship can have.
	 * @return the minimum radius a ship can have.
	 */
	@Basic 
	public static double getMinRadius(){
		return Ship.min_Radius;
	}

	// TODO dit is liskov
	/** 
	 * Checks whether the given radius has a valid value.
	 * 
	 * @param  radius
	 * 		   The radius of the ship.
	 * 
	 * @return True if the radius exceeds the minimal radius
	 * 		   false if the radius is less than the minimal_radius. 
	 * 		   | radius >= Ship.getMin_Radius;
	 */
    @Override
	public boolean isValidRadius(double radius){
		return (super.isValidRadius(radius) && radius >= Ship.getMinRadius());
    }

	
	
//-------- MOVING, TURNING AND ACCELERATING-----
	/**
	 * This method can enable the thrust for this ship.
	 */
	public void thrustOn(){
		this.thrusterActivity = true;
	}
	
	/**
	 * This method can disable the thrust for this ship.
	 */
	public void thrustOff(){
		this.thrusterActivity = false;
	}
	
	/**
	 * This method returns whether the thruster of this ship is enabled or not.
	 * @return Whether the thruster is enabled or not.
	 * 		   True when it is enabled, false otherwise.
	 */
	@Basic
	public Boolean getThrustState(){
		return this.thrusterActivity;
	}
	
	/**
	 * This method sets the activity of the thruster to the given value.
	 * @param thrusterActivity
	 * 		  The new thursterActivity
	 * @effect The thrusterActivity of this ship is equal to the given boolean.
	 */
	public void setThrusterActivity(boolean thrusterActivity){
		if (thrusterActivity == true){
			thrustOn();
		}
		else{
			thrustOff();
		}
	}
	
	/**
	 * This method returns the acceleration that this ship would get when the thruster is enabled.
	 * It is calculated using Newton's second law of motion.
	 * 
	 * @return The acceleration of this ship.
	 * 		   |result == (force/getTotalMass())
	 */
	@Basic
	public double getPossibleAcceleration(){
		return this.getThrustForce()/this.getTotalMass();
	}
	
	/**
	 * Returns the acceleration of this ship.
	 * @return The possible acceleration if the thruster is on.
	 * 		   If the thruster is off, return zero; the ship is not accelerating.
	 * 		   |@see implementation
	 */
	public double getAcceleration(){
		if (this.getThrustState()==true){
			return this.getPossibleAcceleration();
		}
		else{
			return 0.0;
		}
	}
	
	/**
	 * This method returns the force of the thruster of this ship.
	 * @return The force of the thruster of this ship.
	 */
	@Basic
	public double getThrustForce(){
		return this.thrustforce;
	}
	
	/**
	 * This method sets the thrustforce for this ship to a new value.
	 * @param thrustforce
	 * 		  The thrustforce to be set.
	 * @effect The thrustforce is changed to the given value.
	 * 		   This only if the given value is greater than zero, else nothing is changed.
	 * 		   |if (thrustforce > 0){
			   |	then new.thrustforce = thrustforce;
	 */
	public void setThrustForce(double thrustforce){
		if (thrustforce > 0){
			this.thrustforce = thrustforce;
		}
	}
	
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
		if(getThrustState() == true){
			//If the acceleration is negative, then we leave the velocity untouched.
			double a = Math.max(0, getPossibleAcceleration());
			double NewxVelocity = this.getXVelocity() + a*(Math.cos(this.getOrientation()))*duration;
			double NewyVelocity = this.getYVelocity() + a*(Math.sin(this.getOrientation()))*duration;
			this.setVelocity(NewxVelocity, NewyVelocity);	
			}	
	}

	
	/**
	 * Move this ship and all it's on-board belongings with it. 
	 * 
	 * @effect The ship itself is moved, as well as all the entities that are in it.
	 * 		   |super.move(duration);
	 *	
	 *			|for (Bullet bullet: this.bullets)
	 *			|		bullet.move(duration);
	 *
	 *@note Throws imposed via move, handled in documentation there.
	 */
	@Override
	public void move(double duration) throws IllegalPositionException, IllegalDurationException{
		super.move(duration);
		
		for (Bullet bullet: this.bullets){
			bullet.move(duration);
		}
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
		System.out.println("hello ship turning: " + angle);
		double newAngle = this.getOrientation() + angle;
	//	double ScaledAngle = scaleangle(newAngle);
		this.setOrientation(angle);
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
		double ScaledAngle = angle % getMaxOrientation();
		return ScaledAngle;
	}
	
//---------------------BULLETS --------------------------
    
    
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
	@Basic
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
     *         -The bullet is compeletly within this ship. 
     *         | result == ( (bullet != null) 
     *         |       && ((!this.isTerminated()) || (bullet.isTerminated()))
     *         |			 && (!this.hasBullet(bullet)) 
     *         |					&& (bullet.isFullyWithinEntity(this))  )
    }
     */
	@Raw
    public boolean canHaveAsBullet(Bullet bullet) {
    	  return ( (bullet != null) 
    			       && ((!this.isTerminated()) || (bullet.isTerminated()))
    			     		 && (!this.hasBullet(bullet)) 
    			     				&& (bullet.isFullyWithinEntity(this))  );		  
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
	@Raw
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
	 * Adds a 'default' bullet to the bullets loaded on the ship.
	 *  
	 * @post   A new bullet is created withd default properties, it is associated with this ship and 
	 * 		   this ships' collection of bullets is extended.
	 * 		   | new.bullet.getShip() == this
	 * 		   | this.hasBullet(bullet) == true
	 * @throws IllegalRadiusException 
	 * @throws IllegalPositionException 
	 * 		   We are obliged to throw these exceptions, even though we know that the default bullet will be legal.
	 */
	public void loadBullet() throws IllegalPositionException, IllegalRadiusException{
		Bullet bullet = new Bullet(this.getXPosition(), this.getYPosition(), this.getXVelocity(), this.getYVelocity(),
												Bullet.getMinRadius());
		bullet.setShip(this);
		this.bullets.add(bullet);
	}
	
	/**
	 * Adds a certain bullet to this ship.
	 * 
	 * @param bullet
	 * 		  That bullet to add to this ships collection of bullets.
	 * @post   The specific bullet is now within this ship.
	 * 		   | new.bullet.getShip() == this
	 * 		   | this.hasBullet(bullet) == true		
	 * @post  If the bullet was previously in a world, it is removed from the collection of this world.
	 * 		  |if (bullet.getWorld()!=null){
	 *		  | 	then bullet.getWorld().removeEntity(bullet);	
	 *
	 * @throws  If both the bullet and the ship are in a world but not the same one, we throw a bulletexception. 
	 * 			Same thing if this ship can't have the given bullet as a bullet or if the bullet already has a ship.
	 * 		   |(  ((this.getWorld()!=null)&&(bullet.getWorld()!=null)	
	 * 		   |				&&(this.getWorld()!=bullet.getWorld())) 
	 * 		   | 							|| !canHaveAsBullet(bullet) 
	 * 		   | 									|| bullet.getShip() != null   )
	 * 		 
	 */
	public void loadBullet(Bullet bullet) throws IllegalPositionException, IllegalBulletException{
		
		if (((this.getWorld()!=null)&&(bullet.getWorld()!=null)&&(this.getWorld()!=bullet.getWorld())) 
							|| !canHaveAsBullet(bullet) || bullet.getShip()!= null){
			throw new IllegalBulletException(bullet);
		}
		//If the bullet was previously associated to a world, now remove it from that world.
		if (bullet.getWorld()!=null){
			bullet.getWorld().removeEntity(bullet);	
		}
		this.bullets.add(bullet);
		bullet.setShip(this);
		bullet.setPosition(this.getXPosition(), this.getYPosition());
		bullet.setVelocity(this.getXVelocity(), this.getYVelocity());
	}
	
	/**
	 * Adds a specified amount of  default bullets to the ship
	 * 
	 * @param numberOfBullets
	 * 		  The number of bullets to add to this ship.
	 * @effect loadBullet is called upon a "numberOfBullets" amount of times.
	 * 		   |@see implementation
	 * @throws IllegalRadiusException 
	 * @throws IllegalPositionException 
	 * 		   We are obliged to throw these exceptions, even though we know that the default bullet will be legal.
	 */
	public void addMultipleBullets(int numberOfBullets) throws IllegalPositionException, IllegalRadiusException{
		for(int i=0; i<numberOfBullets; i++){
            loadBullet();
       }
	}
	
	/**
	 * This method adds a collection of bullets to its collection.
	 * It only does this if each bullet can in fact be safely added to this ship.
	 * If not, it throws an illegal Bulletexception.
	 * 
	 * @param bullets
	 * 		  The collection of bullets to add to this ship.
	 * @effect For each bullet in the given collection, the loadBullet function is called upon.
	 * 		   That means that for each adding, the documentation of loadBullet applies.
	 * 		   |@see implementation.
	 */
	public void loadBullets(Collection<Bullet> bullets) throws IllegalBulletException, IllegalPositionException{
        for (Bullet bullet : bullets){
            this.loadBullet(bullet);
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
	public void removeBullet(Bullet bullet) throws IllegalEntityException{
		if (this.hasBullet(bullet)){
			bullets.remove(bullet);
			bullet.setShip(null);
		}
		else{
			throw new IllegalEntityException(this);
		}
	}
	
	/**
	 * Fires a bullet by putting it within this world, in front of the ship in the direction 
	 * to where the ship is oriented. This with a small margin and a speed of 250.
	 * 
	 * @post If this ship does not have any bullets or does not belong to a world, nothing happens.
	 * 	
	 * @post The bullet is removed from the ship's collection of bullets and the ship becomes the bullets' source.
	 * 	     | new.bullet.getShip()==null
	 * 		 | new.bullet.getSource() == this
	 *
	 * @effect The bullet is placed next to the ship with a small margin between both, place depending on the ships orientation, 
	 * 	     and its initial velocity is set to 250 orientated in the same direction as the ship.
	 * 		|@see implementation
	 * 
	 * @effect If, upon creation, the bullet is outside of the boundaries of this world, it is immediatly terminated.
	 * 			| if (!(this.getWorld().withinWorldBoundaries(bullet)))
	 * 			| 		then bullet.terminate()
	 * 
	 * @effect If, upon creation, the bullet overlaps with another entity, both are immediatly terminated.
	 * 
	 * 			|for (Entity entity : getWorld().getAllEntities()){
	 *			|	if (bullet.significantOverlap(entity) == true){
	 *			|		then bullet.terminate();
	 *			|		 	 entity.terminate();
	 * 
	 * 
	 * @throws IllegalPositionException 
	 * 		   The position to which the bullet is set must be legal.
	 * @throws IllegalRadiusException
	 * 		   The radius of the fired bullet must be legal.
	 * @throws IllegalShipException 
	 * 		   The ship must be a valid source to the fired bullet.
	 */
	public void fireBullet() throws IllegalPositionException, IllegalRadiusException, IllegalShipException{
		if (this.getNbOfBullets()!= 0 && belongsToWorld()){
			Bullet bullet = this.getBullets().iterator().next();
			
			double margin = 1.05;
			
			double bulletXPos = this.getXPosition() + margin*(this.getRadius() + bullet.getRadius())*Math.cos(this.getOrientation());
			double bulletYPos=  this.getYPosition() + margin*(this.getRadius() + bullet.getRadius())*Math.sin(this.getOrientation());
		
			double xSpeed = getInitialBulletSpeed()*Math.cos(this.getOrientation());
			double ySpeed = getInitialBulletSpeed()*Math.sin(this.getOrientation());
			
			
			this.removeBullet(bullet);
			bullet.setPosition(bulletXPos, bulletYPos);
			bullet.setSource(this);
//			bullet.setShip(null);
			
			//bullet is now set to where it will start its movement, after some checks.
			
			
			// Check whether the bullet is within the worlds' boundaries, if not: terminate it.
			if (!(this.getWorld().withinWorldBoundaries(bullet))){
				//Bullet is outside world upon creation. 
				bullet.terminate();
				//Get out of this method, further checks don't apply anymore.
				return;
			}
			
			// Check whether this bullet overlaps with another entity upon creation, if so: delete both.
			for (Entity entity : getWorld().getAllEntities()){
				if (bullet.significantOverlap(entity) == true){
					
					bullet.terminate();
					entity.terminate();

					//Further running of this code is unneccesary and possibly unsafe
					return;
				}
			}
	
			this.getWorld().addEntity(bullet);
			bullet.setWorld(this.getWorld());
			
			// The bullet is in a legal spot and can start moving. It's velocity is now assigned.
			bullet.setVelocity(xSpeed, ySpeed);	
			
		}
	}
	
	/**
	 * Returns the initial speed at which a ship fires a bullet.
	 * @return the initial speed at which a ship fires a bullet.
	 */
	@Basic
	public double getInitialBulletSpeed(){
		return 250.0;
	}
	
	/**
	 * This method simply checks whether this ship belongs to a world.
	 * @return True if and only if this ship belongs to a world.
	 */
	public boolean belongsToWorld(){
		 if (getWorld() != null) {
			 return true;
		 }
		 return false;	
	}
	
	/**
	 * This method handles the collision in case one of the colliding
	 * entities is a ship. It checks what kind of collision we our dealing with 
	 * and resolves (this side of) the collision. 
	 * 
	 * @param entity
	 * 		  The entity with which this ship collides.
	 * 
	 * @effect If this ship collides with another ship, 
	 * 		  the collision is handled as a casual collision.
	 * 		  If it collides with a deadly entity, it is terminated, 
	 * 		  but not if that entity is a bullet fired by this ship.
	 * 		  In that case, it is loaded back upon the ship.
	 * 		  | @see implementation
	 */
	@Override
	public void handleOtherEntityCollision(Entity entity) throws IllegalPositionException, IllegalBulletException{
		
		if(entity instanceof Ship){
			this.handleCasualCollision(entity);
		}
		if (entity.isDeadly() == true){
			if(entity instanceof Bullet){
				if(((Bullet) entity).getSource() == this){
					// The Bullet is first placed to the centre of this ship,
					// so that it is fully within this ship and can be loaded.	
					((Bullet) entity).setPosition(this.getXPosition(),this.getYPosition());
					
					this.loadBullet((Bullet) entity);
						return;
				}
			}
			this.terminate();
		}		
	}
	
	/**
	 * This method teleports this ship to a random
	 * place in this world.
	 * 
	 * @post If the ship overlaps with another entity
	 * 		 upon placement, the ship is immediatly terminated.
	 * 		 The other entity is left untouched.
	 * 		 | if(new.overlapsWithOther())
	 *		 |		 then this.terminate()
	 *
	 * @post If the ship does not overlap in its new position,
	 * 		 if takes on the random position as its new position.
	 * 		 |radius = this.getRadius()
	 * 		 |randomXCoord = radius + Math.random()*(this.getWorld().getWidth()-2*radius);
	 *	     |rancomYCoord = radius + Math.random()*(this.getWorld().getHeight()-2*radius);
	 * 		 | new.getXPosition = randomXCoord
	 * 		 | new.getYPosition = randomYCoord
	 */
	public void teleport(){
		
		double radius = this.getRadius();
		double randomXCoord = radius + Math.random()*(this.getWorld().getWidth()-2*radius);
		double rancomYCoord = radius + Math.random()*(this.getWorld().getHeight()-2*radius);
		
		try{
			this.setPosition(randomXCoord, rancomYCoord);
			
			if(this.overlapsWithOther()){
				this.terminate();
			}
		} catch (Exception exception) {
            this.terminate();
        }	
	}
	
    
// -----------------------  VARIABLES (DEFAULTS & FINAL) --------
	
	/**
	 * The variable thrust defines whether the thrust of this ship is enabled or not.
	 * It is initialised as being disabled.
	 */
	private boolean thrusterActivity = false;
	
	/**
	 * A variable registering the bullets owned by this ship.
	 */
    private Set<Bullet> bullets = new HashSet<Bullet>();

	/**
	 * Variable registering the radius of this Ship.
	 */
	private double radius = min_Radius;

	/**
	 * Variable registering the minimum allowed Radius.
	 * The minimum radius may change in the future. 
	 * But it will always remain the same for all Ships.
	 *
	 */
	private static double min_Radius = 10.0;
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation = min_Orientation;
	
	/**
	 * Variable registering the minimum allowed orientation.
	 */
	private static final double min_Orientation = 0.0;


	/**
	 * Variable registering the maximum allowed orientation.
	 */
	private static final double max_Orientation = 2.0*Math.PI;
	
    /**
     * Variable registering the density of this ship.
     */
    private double density = min_Density;
  
    /**
     * Variable registering the minimum allowed density.
     */
    
    private static final double min_Density = 1.42*(Math.pow(10.0, 12.0));
    
    /**
     * Variable registering the Minimum allowed mass.
     */
    private final double min_Mass = min_Density*(4.0/3.0)*Math.PI*(Math.pow(radius, 3.0));
    
	/**
	 * A variable defining the force that an active thruster can exert on a ship.
	 */
	private double thrustforce = 1.1E18;
	
		
		
		
		//-----------------------------------
		// PROGRAMS
		//------------------------------------
		
		
		
		private Program program = null;
		
		public void setProgram(Program program){
			System.out.println("SETTING PROGRAM ON SHIP");
			program.setShip( this );
			this.program = program;
		}
		
		public Program getProgram(){
			return this.program;
		}
		
		public List<Object> executeProgram(double dt){
			System.out.println("SHIP EXECUTEPROGRAM");
			return this.getProgram().run(dt);
		}
		

}
