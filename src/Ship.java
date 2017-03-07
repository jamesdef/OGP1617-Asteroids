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
 *         
 *           
 *        
 * @note    The annotation @value indicates that objects of this class
 *          must be considered to be (immutable) values rather than
 *          mutable objects
 *          
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
	 * @post   The lowest possible velocity is zero, it can only move forward.
	 * 		   |new.getMinVelocity == 0
	 * 
	 * @post   The highest possible velocity is 300000 (km/s) it can never exceed this.
	 * 		   |new.getMaxVelocity == 300000
	 * 
	 *   // NIET ZEKER DAT ONDERSTAANDE POST HIER THUISHOORT
	 * @post   The lowest possible radius is 10 km. (This minimum can easily be changed).
	 * 		   |new.getMinRadius == 10
	 * 
	 * @post   The orientation of the ship will always be a value between 0(Right) and 2PI(Right).
	 * 		   |0 <= new.getOrientation < 2*PI
	 * 
	 * @effect The given parameters are set as the properties of the new ship.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *	       |setOrientation(orientation);
	 * 
	 *@note    Any new ship initialized with this constructor
     * 		   will satisfy all its class invariants.      
	 * 
	 */
	
	// Position X and Y are described seperatly, this proves to be the easiest to work with. Same goes for speed.
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation){
		
		// At this point we can invoke our mutators, because the range is known.
		
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);
		
	}
	
	

	/**
	 * Initialize this new ship with their parameters (position,speed,radius,orientation) set to their lowest possible Value.
	 * 
	 * @effect This new ship is initialized in the center of the grid: (0,0)
	 * 			xPosition = 0
	 * 			yPosition = 0
	 * 			It's radius will be set to it's lowest possible value.
	 * 			It's velocity will be set to 0. The ship will not be moving.
	 * 			It's orientation is to the right, i.e.: it has an angle of 0 radians to the x-axis.
	 * 
	 * 			
	 */
	public Ship(){
		this(0.0,0.0,0.0,0.0,Ship.getMinRadius(),0.0);
	}
	 
	/**
	 * Variable registering the xPosition of this Ship.
	 */
	private double xPosition = 0;
	
	/**
	 * Variable registering the yPosition of this Ship.
	 */
	private double yPosition = 0;

	
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
	private double orientation = 0;
	
	/**
	 * Variable registering the radius of this Ship.
	 */
	private double radius = getMinRadius();
	
	
	

// The inspectors 
	
	/**
	 * Return the x-coordinate of this ship.
	 * 
	 * @return
	 */
	@Basic
	public double getxPosition(){
		return this.xPosition;
		
	}	
	

	/**
	 * Return the y-coordinate of this ship.
	 * 
	 * 
	 */
	@Basic
	public double getyPosition(){
		return this.yPosition;
		
	}	
	
	/**
	 * Return the velocity of this ship, in the x-direction.
	 * 
	 */
	@Basic
	public double getxVelocity(){
		return this.xVelocity;
		
	}
	
	/**
	 * Return the velocity of this ship, in the y-direction.
	 * 
	 */
	@Basic
	public double getyVelocity(){
		return this.yVelocity;
		
	}
	
	/**
	 * Return the velocity of this ship, in the y-direction.
	 * 
	 */
	@Basic
	public double getVelocity(){
		return Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2));
		
	}
	
	
	/**
	 *  Return the orientation of this ship.
	 * @return
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;
		
	}	
	
	/**
	 *  Return the Radius of this ship.
	 * @return
	 */
	@Basic @Immutable
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Returns the minimum Radius that a ship needs to have.
	 *
	 */
	@Basic
	public static double getMinRadius() {
		return 10; 
	}
	
	/**
	 * Returns the minimum velocity that a ship can have.
	 * @return
	 */
	@Basic
	public double getMinVelocity() {
		return 0;
	}
	
	/**
	 * Returns the maximum velocity that a ship can have.
	 * @return
	 */
	@Basic
	public double getMaxVelocity() {
		return 300000;
	}
	
	
 
//-----------------------------------------------------------------------------------------------------------
	
// The Mutators... (Position: Defensively | Velocity: Total | Orientation: Nominally | Radius: Defensively)
	
	/**
	 * 
	 * @param xPostion
	 * 		  The new x-coordinate for this ship.	
	 * @param yPosition
	 * 		  The new y-coordinate for this ship.
	 * 
	 * @post 
	 */
	public void setPosition(double xPostion, double yPosition){
		
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
		double total_velocity = (Math.sqrt(Math.pow(getyVelocity(),2)+ Math.pow(getxVelocity(),2)));
		
		if ((total_velocity >= getMinVelocity()) &&
			(total_velocity <= getMaxVelocity()))
					xVelocity = getxVelocity();
					yVelocity = getyVelocity();
		else if (total_velocity > getMaxVelocity())
					xVelocity = 150000*Math.sqrt(2);
					yVelocity = 150000*Math.sqrt(2);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
					
		
	}
	
	
	
	public void setRadius(double Radius){
		
	}
	
	public void setOrientation(double orientation){
		
	}
	
	
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
