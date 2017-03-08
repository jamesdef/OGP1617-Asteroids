import be.kuleuven.cs.som.annotate.*;

/**
 * A class for dealing with ships that have a certain position, radius, speed and orientation.
 * 
 * @invar 	The lowest possible velocity is zero, it can only move forward.
 * 			|new.getMinVelocity == 0
 * 
 * @invar   The highest possible velocity is 300000 (km/s) it can never exceed this.
 * 	      	|new.getMaxVelocity == 300000
 * 
 * @invar	The orientation of the ship must be a valid value.
 * 			|isValidOrientation(getOrientation())
 * 
 * @Invar 	The radius of each ship must be a valid value.
 * 			|isValidRadius(getRadius())
 * 
 * @Invar   The coordinates of a ship have to be finit numbers.
 * 			|isValidCoordinate(getxPosition);
 * 			|isValidCoordinate(getyPosition);       
 *       

 * @version 1.0     
 * @author James Defauw & Michiel De Koninck
 
 */

public class Ship {

//Constructors: #2
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
//	 * @post   The lowest possible velocity is zero, it can only move forward.
//	 * 		   |new.getMinVelocity == 0
//	 * 
//	 * @post   The highest possible velocity is 300000 (km/s) it can never exceed this.
//	 * 		   |new.getMaxVelocity == 300000
//	 * 
//	 *   // NIET ZEKER DAT ONDERSTAANDE POST HIER THUISHOORT
//	 * @post   The lowest possible radius is 10 km. (This minimum can easily be changed).
//	 * 		   |new.getMinRadius == 10
//	 * 
//	 * @post   The orientation of the ship will always be a value between 0(Right) and 2PI(Right).
//	 * 		   |0 <= new.getOrientation < 2*PI
	 * 
	 * @effect The given parameters are set as the properties of the new ship.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *	       |setOrientation(orientation);
	 * 
	 *@note    Any new ship initialized with this constructor
     * 		   will satisfy all its class invariants. The setters will see to this in their implementation.
     * 			      
	 * 
	 */
	
	// Position X and Y are described seperatly, this proves to be the easiest to work with. Same goes for speed.
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation){
		
		// At this point we can invoke our mutators. They will further impose the requirements posed above.
		
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);
		
	}
	
	

	/**
	 * Initialize this new ship with their parameters (position,speed,radius,orientation) set to their lowest possible Value.
	 * 
	 * @effect This new ship is initialized in the center of the grid: (0,0)
	 * 			xPosition = 0.0
	 * 			yPosition = 0.0
	 * 			It's radius will be set to it's lowest possible value.
	 * 			It's velocity will be set to it's minimal value: 0. The ship will not be moving.
	 * 			It's orientation is to the right (Minimum value=0), i.e.: it has an angle of 0 radians to the x-axis.
	 * 			|this(0.0,0.0,getMinVelocity(),getMaxVelocity(),Ship.getMinRadius(),Minimum_Orientation)
	 * 			
	 */
	public Ship(){
		this(0.0,0.0,getMinVelocity(),getMaxVelocity(),Ship.getMinRadius(),Minimum_Orientation);
	}
	 
	/**
	 * Variable registering the xPosition of this Ship.
	 */
	private double xPosition = 0.0;
	
	/**
	 * Variable registering the yPosition of this Ship.
	 */
	private double yPosition = 0.0;

	
	/**
	 * Variable registering the xVelocity of this Ship.
	 */
	private double xVelocity = getMinVelocity();
	
	
	/**
	 * Variable registering the yVelocity of this Ship.
	 */
	private double yVelocity = getMinVelocity();
	
	/**
	 * Variable registering the orientation of this Ship.
	 */
	private double orientation = Minimum_Orientation;
	
	/**
	 * Variable registering the radius of this Ship.
	 */
	private double radius = getMinRadius();
	
	/**
	 * Variable registering the maximum allowed orientation
	 */
	private static double Maximum_Orientation = 2.0*Math.PI;
	
	/**
	 * Variable registering the minimum allowed orientation
	 */
	private static double Minimum_Orientation = 0.0;
	
	

// The inspectors 
	
	/**
	 * Return the x-coordinate of this ship.  
	 * @return the x-coordinate of this ship.
	 */
	@Basic
	public double getxPosition(){
		return this.xPosition;	
	}	
	

	/**
	 * Return the y-coordinate of this ship.
	 * @return the y-coordinate of this ship.
	 * 
	 */
	@Basic
	public double getyPosition(){
		return this.yPosition;	
	}	
	
	/**
	 * Return the velocity of this ship, in the x-direction.
	 * @return the horizontal velocity of this ship.
	 */
	@Basic
	public double getxVelocity(){
		return this.xVelocity;
		
	}
	
	/**
	 * Return the velocity of this ship, in the y-direction.
	 * @return the vertical velocity of this ship.
	 */
	@Basic
	public double getyVelocity(){
		return this.yVelocity;
		
	}
	
	/**
	 * Return the total velocity of this ship.
	 * @return the total velocity of this ship.
	 * @note The total velocity of a ship is computed:
	 * 		 by taking the square root of the sum of the
	 * 		 secondpowers of the horizontal and vertical velocity.
	 * 		-> sqrt(Vx^2+Vy^2)
	 */
	@Basic
	public double getVelocity(){
		return Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2));
		
	}
	
	
	/**
	 *  Return the orientation of this ship.
	 * @return the orientation of this ship.
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;	
	}	
	
	/**
	 *  Return the radius of this ship.
	 * @return the radius of this ship.
	 */
	@Basic
	public double getRadius(){
		return this.radius;
	}
	
// STEPHEN HUANG: Toch best om geen getMethodes aan te maken voor zulk een simpele dingen. Gewoon variabele maken.
	
	/**
	 * Returns the minimum Radius that a ship needs to have.
	 * 
	 * @return The lowest possible radius for each ship must be non-negative.
	 * 			| result >= 0
	 * 
	 * @note We have chosen to make this a static method,
	 * 		 because all ships must have this boundary.
	 * 		 
	 */
	@Basic
	public static  double getMinRadius() {
		return 10; 
	}
	
	/**
	 * Returns the minimum velocity that a ship can have.
	 * @return the minimum velocity that a ship can have.
	 */
	@Basic
	public static double getMinVelocity() {
		return 0;
	}
	
	/**
	 * Returns the maximum velocity that a ship can have.
	 * @return the maximum velocity that a ship can have.
	 */
	@Basic
	public static double getMaxVelocity() {
		return 300000;
	}
	
	
	
	/** DEFENSIVE PROGRAMMING
	 * 
	 * @param xPostion
	 * 		  The new x-coordinate for this ship.	
	 * @param yPosition
	 * 		  The new y-coordinate for this ship.
	 * @post The x-coordinate of the  ship equals the given xPosition.
	 * 		 |new.getxPosition == xPosition
	 * @post The y-coordinate of the  ship equals the given yPosition.
	 * 		 |new.getyPosition == yPosition
	 * 
	 * @throws IllegalArgumentException
	 * 		 The given xPosition or yPosition is not valid.
	 * 	     | !isValidPosition(xPosition,yPosition)
	 */
	public void setPosition(double xPosition, double yPosition) 
			throws IllegalArgumentException{
		if (!isValidPosition(xPosition,yPosition))
			throw new IllegalArgumentException();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * @param xPostion
	 * 		  The x-coordinate for this ship.	
	 * @param yPosition
	 * 		  The y-coordinate for this ship.	 
	 * @return True if and only if neither of the coordinates is either infinity or NaN
	 *		  | result == (xPosition != Double.NaN && yPosition != Double.NaN && xPosition != Double.POSITIVE_INFINITY && xPosition != Double.NEGATIVE_INFINITY && yPosition != Double.POSITIVE_INFINITY && yPosition != Double.NEGATIVE_INFINITY)
	 *
	 */
	public static boolean isValidPosition(double xPosition, double yPosition){
		return (xPosition != Double.NaN && yPosition != Double.NaN && xPosition != Double.POSITIVE_INFINITY && xPosition != Double.NEGATIVE_INFINITY && yPosition != Double.POSITIVE_INFINITY && yPosition != Double.NEGATIVE_INFINITY);
	}
	
	/** TOTAL PROGRAMMING 
	 * 
	 * @param xVelocity
	 * 		  The new velocity in the x-direction for this ship.
	 * @param yVelocity
	 * 		  The new velocity in the y-direction for this ship.
	 * 
	 * @post If the given velocity is within the range of acceptable velocities,
	 * 		 the  velocity of this ship is equal to the given velocity.
	 * 		|if ((sqrt(xVelocity^2 + yVelocity^2) >= getMinVelocity()) &&
	 * 			((sqrt(xVelocity^2 + yVelocity^2)<= getMaxVelocity())))
	 * 		|	then new.getxVelocity() == xVelocity && new.getyVelocity() == yVelocity
	 * 
	 * @post If the total velocity exceeds the maximum velocity, 
	 * 		 both velocities are set to be the same and at their maximum value.
	 * 		|if ((sqrt(xVelocity^2 + yVelocity^2) > getMaxVelocity())))
	 * 			then new.getxVelocity() =new.getyVelocity() = 150000*sqrt(2)
	 * 
	 * @post If either of the given velocities is below the minimum value,
	 * 		 both velocities are left untouched.
	 * 		|if (xVelocity < getMinVelocity() or yVelocity < getMinVelocity())
	 * 		|	then new.getxVelocity = this.getyVelocity() && new.getxVelocity() = this.getyVelocity()
	 * 
	 * @note The last postcondition is not really necessary.
	 * 		 Everything that is not changed within the method is left  untouched.
	 */
	public void setVelocity(double xVelocity, double yVelocity){
		// For readability, we computed total_velocity seperatly
		double total_velocity = (Math.sqrt(Math.pow(getyVelocity(),2)+ Math.pow(getxVelocity(),2)));
		
		if ((total_velocity >= getMinVelocity()) &&
			(total_velocity <= getMaxVelocity()))
					xVelocity = getxVelocity();
					yVelocity = getyVelocity();
		if (total_velocity > getMaxVelocity())
					xVelocity = 150000*Math.sqrt(2);
					yVelocity = 150000*Math.sqrt(2);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
					
		
	}
	
	
	/** Defensive Programming
	 * 
	 * @param radius
	 * 		  The new, given radius of the ship.
	 * 
	 * @post The radius of the ship is now equal to the given radius.
	 * 		|new.getRadius() == radius	
	 * 
	 * @throws  IllegalRadiusException
	 * 		   The given radius is not a valid radius.
	 * 		   | ! isValidRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalArgumentException{
		if (!isValidRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
	}
	
	public static boolean isValidRadius(double radius){
		return radius >= Ship.getMinRadius();
	}
	
	
	
	/** Nominal Programming
	 * 
	 * @param orientation
	 * 		  The new, given orientation of the ship.
	 * @pre The given orientation must be a valid one.
	 * 		|isValidOrientation(orientation)
	 * @post The orientation of the ship is now changed to the given value
	 *		|new.getOrientation()== orientation
	 */
	public void setOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;	
	}
	
        
    /**
     * 
     * @param orientations
     * 		  The orientation of which we need to check wether it is legal.
     * 
     * @return True if and only if the given orientation is within the boundaries opposed upon orientation.
     * 		   |result == (Minimum_Orientation <= orientation) && (orientation < Maximum_Orientation)
     * 
     */
    public static boolean isValidOrientation(double orientation){
    	return (Minimum_Orientation <= orientation) && (orientation <= Maximum_Orientation);	
    }
    	
    			
 
    
 //-----------------JAMES----------------------------------------------------------------------
	
    //thrust is total programming
	public void thrust(double acceleration){
		double a = Math.max(0, acceleration);
		double xVelocity = 0 + a*(Math.cos(this.getOrientation()));
		double yVelocity = 0 + a*(Math.sin(this.getOrientation()));
		
		this.setVelocity(xVelocity, yVelocity);
		
	}
	
	/**
	 * 
	 * @param duration
	 * 			The duration of the movement
	 * @post The duration is non-negative 
	 */
	
	public void move(double duration){
		
		
	}
	
	public void turn(double angle){
		
	}

	
	
}
