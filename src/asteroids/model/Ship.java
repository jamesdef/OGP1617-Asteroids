package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

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
 * @invar 	The radius of each ship must be a valid value.
 * 			|isValidRadius(getRadius())
 *
 * @invar   The mass of a ship must be valid.
 * 		    |isValidMass(this.getEntityMass)
 * 
 * @invar   The density of a ship must be valid.
 * 		    |isValidDensity(this.getDensity)
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
	 * 		   |super(xPosition, yPosition, xVelocity, yVelocity, radius, orientation);
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

// ------------------------------ MASS & DENSITY-------------
   
	/**
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
	@Raw
	public boolean isValidMass(double mass){
        return (mass >= 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity());
	}
	
	/**
	 * Returns the mass of this ship.
	 * @return the mass of this ship.
	 */
	@Basic
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Returns the density of this ship.
	 * @return the density of this ship.
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
			density = Min_Density;
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
		return (density >= Min_Density);
	}
        
    //MASS - Total programming
    
    /** 
     * This method returns the sum of all the masses of the bullets on this ship.
     * 
     * @return The sum of all the masses of the bullets on this ship.
     * 		   It does this by adding the mass of all bullets together
     * 			|@see implementation
     * 		   
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
	
	
// ------------------------------------ Radius --------------------------
	
 
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

	
	
//-------- MOVING, TURNING AND ACCELERATING-----
	
	/**
	 * The variable thrust defines whether the thrust of this ship is enabled or not.
	 * It is initialised as being disabled.
	 */
	private boolean thrusterActivity = false;
	
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
	public Boolean getthrustState(){
		return this.thrusterActivity;
	}
	
//	/**
//	 * This method sets the activity of the thruster to the given value.
//	 * @param thrusterActivity
//	 * 		  The new thursterActivity
//	 * @effect The thrusterActivity of this ship is equal to the given boolean.
//	 */
//	private void setThrusterActivity(boolean thrusterActivity){
//		if (thrusterActivity == true){
//			thrustOn();
//		}
//		else{
//			thrustOff();
//		}
//	}
	
	/**
	 * This method returns the acceleration that this ship would get when the thruster is enabled.
	 * It is calculated using Newton's second law of motion.
	 * 
	 * @return The acceleration of this ship.
	 * 		   |return (force/getTotalMass())
	 */
	@Basic
	public double getPossibleAcceleration(){
		return getThrustForce()/getTotalMass();
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
		double ScaledAngle = angle % getMax_Orientation();
		return ScaledAngle;
	}
	
//---------------------BULLETS --------------------------
	
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
     *         -This ships' radius is bigger than that of the bullet.
     *         | result == ( (bullet != null) 
     *         |       && ((!this.isTerminated()) || (bullet.isTerminated()))
     *         |			 && (!this.hasBullet(bullet)) 
     *         |					&& (bullet.getRadius()<this.getRadius())  )
    }
     */
	@Raw
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
	 * @effect Add a new 'default' bullet to the collection of this ship.
	 * 		   A new bullet is created withd default properties, it is associated with this ship and 
	 * 		   this ships' collection of bullets is extended.
	 * 		   | new.bullet.getShip() == this
	 * 		   | this.hasBullet(bullet) == true
	 * @throws IllegalRadiusException 
	 * @throws IllegalPositionException 
	 * 		   We are obliged to throw these exceptions, even though we know that the default bullet will be legal.
	 */
	public void loadBullet() throws IllegalPositionException, IllegalRadiusException{
		Bullet bullet = new Bullet(this.getxPosition(), this.getyPosition(), this.getxVelocity(), this.getyVelocity(),
												Bullet.getMinRadius(), this.getOrientation());
		bullet.setShip(this);
		this.bullets.add(bullet);
	}
	
	/**
	 * Adds a specified amount of bullets to the ship
	 * 
	 * @param numberOfBullets
	 * 		  The number of bullets to add to this ship.
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
	 * Fires a bullet by putting it within this world, in front of the ship in the direction 
	 * to where the ship is oriented. This with a small margin and a speed of 250.
	 * 
	 * @post If this ship does not have any bullets or does not belong to a world, nothing happens.
	 * 	
	 * @post The bullet is removed from the ship's collection of bullets and the ship becomes the bullets' source.
	 * 	     | new.bullet.getShip()==null
	 * 		 | new.bullet.getSource() == this
	 *
	 * @post The bullet is placed next to the ship with a small margin between both, place depending on the ships orientation, 
	 * 	     and its initial velocity is set to 250 orientated in the same direction as the ship.
	 * 		|@see implementation
	 * 
	 * @post If, upon creation, the bullet is outside of the boundaries of this world, it is immediatly terminated.
	 * 		| if (!(this.getWorld().withinWorldBoundaries(bullet)))
	 * 		| 		then bullet.terminate()
	 * 
	 * @post If, upon creation, the bullet overlaps with another entity, both are immediatly terminated.
	 * 
	 * 			|for (Entity entity : getWorld().getAllEntities()){
	 *			|	if (bullet.overlap(entity) == true){
	 *			|		then bullet.terminate();
	 *			|		 	 entity.terminate();
	 * 
	 * 
	 * @throws IllegalPositionException 
	 * 		   The position to which the bullet is set must be legal.
	 */
	public void fireBullet() throws IllegalPositionException, IllegalRadiusException{
		if (this.getNbOfBullets()!= 0 && belongsToWorld()){
			Bullet bullet = this.getBullets().iterator().next();
			
			double margin = 1.05;
			
			double bulletXPos = this.getxPosition() + margin*(this.getRadius() + bullet.getRadius())*Math.cos(this.getOrientation());
			double bulletYPos=  this.getyPosition() + margin*(this.getRadius() + bullet.getRadius())*Math.sin(this.getOrientation());
		
			double Bulletspeed = 250;
			double xSpeed = Bulletspeed*Math.cos(this.getOrientation());
			double ySpeed = Bulletspeed*Math.sin(this.getOrientation());
			
			this.removeBullet(bullet);
			bullet.setSource(this);
			
			//bullet is now set to where it will start its movement, after some checks.
			bullet.setPosition(bulletXPos, bulletYPos);
			
			// Check whether the bullet is within the worlds' boundaries, if not: terminate it.
			if (!(this.getWorld().withinWorldBoundaries(bullet))){
				//Bullet is outside world upon creation. 
				bullet.terminate();
				//Get out of this method, further checks don't apply anymore.
				return;
			}
			
			// Check whether this bullet overlaps with another entity upon creation, if so: delete both.
			for (Entity entity : getWorld().getAllEntities()){
				if (bullet.overlap(entity) == true){
					
					//If somehow the overlapping entity is the one that fired the bullet, 
					// the bullet is added once more to the collection of its bullets.
					if (bullet.getSource() == entity){
						// this ship is in fact the overlapping entity: load a bullet to this ship.
						// The bullet will be terminated anyway upon exiting this if-clause.
						if (this.equals(entity)){
							this.loadBullet();	
						}
					}
					bullet.terminate();
					entity.terminate();
					
					//Further running of this code is unneccesary and possibly unsafe
					return;
				}
			}
			
			// The bullet is in a legal spot and can start moving. It's velocity is now assigned.
			bullet.setVelocity(xSpeed, ySpeed);	
		}
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
    
	
	/**
	 * A variable defining the force that an active thruster can exert on a ship.
	 */
	private double thrustforce = 1.1E21;
}
