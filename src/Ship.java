import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class for dealing with ships that have a certain position, radius, speed and orientation.
 * 
 * 		-	Work In Progress
 * 
 * @author James Defauw & Michiel De Koninck
 *
 */

public class Ship {

//Constructors: #2
	/**
	 * Initialize this new ship with given position,radius, speed and orientation.
	 * 
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
		       |setVelocity(xVelocity,yVelocity);
		       |setRadius(radius);
		       |setOrientation(orientation);
	 * 
	 *      
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
	public double getxPosition(){
		return this.xPosition;
		
	}	
	

	/**
	 * Return the y-coordinate of this ship.
	 * 
	 * 
	 */
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
	 *  Return the orientation of this ship.
	 * @return
	 */
	public double getOrientation(){
		return this.orientation;
		
	}	
	
	/**
	 *  Return the Radius of this ship.
	 * @return
	 */
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public static double getMinRadius() {
		return 10; 
	}
	
	public double getMinVelocity() {
		return 0;
	}

//-----------------------------------------------------------------------------------------------------------
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
// The Mutators...
	
	
}
