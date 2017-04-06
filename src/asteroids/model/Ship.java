package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;


/**
 * A class for dealing with ships that have a certain position, radius, speed and orientation.
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

public class Ship extends Entity {

	//-------------------------------   Constructors: #2 --------------------------------------------
	/**
	 * Initialize this new ship with given position,radius, speed and orientation.
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
	 * @effect The given parameters are set as the properties of the new ship.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *	       |setOrientation(orientation);
	 * 
	 *@note    Any new ship initialized with this constructor
	 * 		   will satisfy all its class invariants. The setters will see to this in their implementation.
	 */

	// Position X and Y are described seperatly, this proves to be the easiest to work with. Same goes for velocity.
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation, double mass, double density, World world) 
							throws IllegalPositionException, IllegalRadiusException{
		
		// At this point we can invoke our mutators. They will see to it that the class invariants hold at all times.
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);	
	}


	/**
	 * Initialize this new ship with their parameters (position,speed,radius,orientation) set to their lowest possible values.
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
		this(0.0,0.0,Min_Velocity,Min_Velocity,Min_Radius,Min_Orientation,0.0,Min_Density, null);
	}

	// -----------------------  VARIABLES (DEFAULTS & FINAL) --------

	/**
	 * Variable registering the radius of this Ship.
	 * @Override
	 */
	private double radius = Min_Radius;

	/**
	 * Variable registering the minimum allowed Radius.
	 * The minimum radius may change in the future. 
	 * But it will always remain the same for all Ships.
	 * @Override
	 */
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
     * @Override
     */
    private double mass = Min_Mass;
    
    
	// ------------------------   The inspectors -------------------------------------------------------

    
    //MASS
    
    
    /**
     * This returns the sum of all bullets' this ship has masses
     */
    
    public double getMassOfBullets(){
    	return 0;
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






	
	
	
	//-------- MOVING, TURNING AND ACCELARATING-----

	/**
	 *  Raises the velocity of the ship based on a certain, given acceleration and the ship's orientation.
	 * 
	 * @param acceleration
	 * 
	 * @post if the given acceleration is negative
	 * 		 The velocity is left untouched
	 * 		 |if acceleration < 0
	 * 		 | 		then new.getxVelocity() = getxVelocity()
	 * 	     | 		then new.getyVelocity() = getyVelocity()
	 *
	 * @effect The velocity is changed, dependent on the given acceleration.
	 * 	      This is only the case if the calculated new velocity is legal.
	 * 		  The method setVelocity makes sure that this is the case.
	 *		 |NewxVelocity = this.getxVelocity() + a*(Math.cos(this.getOrientation()));
	 *	     |NewyVelocity = this.getyVelocity() + a*(Math.sin(this.getOrientation()));
	 * 		  |this.setVelocity(NewxVelocity, NewyVelocity);
	 */
	public void thrust(double acceleration){
		//If the acceleration is negative, then we leave the velocity untouched.
		double a = Math.max(0, acceleration);
		double NewxVelocity = this.getxVelocity() + a*(Math.cos(this.getOrientation()));
		double NewyVelocity = this.getyVelocity() + a*(Math.sin(this.getOrientation()));

		this.setVelocity(NewxVelocity, NewyVelocity);	
	}

	/**
	 *  Move the ship, given a certain duration.
	 * 
	 * @param duration
	 * 		  The duration of the movement, given in seconds
	 * 
	 * @throws IllegalDurationException
	 * 		   If the duration is not valid, the method throws an exception
	 * 		 | !isValidDuration(duration)
	 * 		   
	 * @throws IllegalPositionException 
	 * 		   If the position is not valid, the method throws an exception.
	 * 		   | !isValidPosition(newxPosition, newyPosition)
	 * 
	 * @effect The new position of the ship is calculated by adding the duration multiplied with the velocity, 
	 *         to the positon of the ship.
	 *        |newxPosition = this.getxPosition() + (duration)*(this.getxVelocity());
	 *	      |newyPosition = this.getyPosition() + (duration)*(this.getyVelocity());
	 *	       |this.setPosition(newxPosition, newyPosition);
	 * 
	 */
	public void move(double duration) throws IllegalPositionException, IllegalDurationException{
		if (!isValidDuration(duration)){
			throw new IllegalDurationException(duration);
		}
		double newxPosition = this.getxPosition() + (duration)*(this.getxVelocity());
		double newyPosition = this.getyPosition() + (duration)*(this.getyVelocity());

		this.setPosition(newxPosition, newyPosition);

	}

	/**
	 *  Check whether the given duration is legal.
	 * 
	 * @param duration
	 * 		  The duration of the specific movement of the ship
	 * @return true if the duration is a non-negative number and finite.
	 * 		   | return (duration >= 0 && !Double.isNaN(duration) && (duration != Double.POSITIVE_INFINITY))
	 */			
	public boolean isValidDuration(double duration){
		return ((duration >= 0) && (!Double.isNaN(duration)) && (duration != Double.POSITIVE_INFINITY));
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
	
	
	public int getNbOfBullets(){
		return 0;
	}
	
	public void addBullet(){
		
	}
	
	public void addMultipleBullets(int numberOfBullets){
		
	}
	
	public void removeBullet(){
		
	}
	
	public void fireBullet(){
		//if not in world, can't fire
		
	}
	
	public boolean canPlaceBullet(){
		//if collides with other entity upon placement
		
		
		//if not entirely in world, destroy
		
		
		//returns true if bullet can be placed and fired next
	}
	
}
