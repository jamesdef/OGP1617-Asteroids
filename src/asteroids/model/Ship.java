package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class for dealing with ships that have a certain position, radius, speed and orientation.
 * 
 * @invar 	The lowest possible velocity is zero, it can only move forward.
 * 			|getxVelocity() >= 0
 * 			|getyVelocity() >= 0
 * 
 * @invar   The highest possible velocity is 300000 (km/s) the ship can never exceed this speed.
 * 	      	|!=exceedsMaxVelocity(getxVelocity(), getyVelocity())
 *  * 
 * @invar	The orientation of the ship must be a valid value.
 * 			|isValidOrientation(getOrientation())
 * 
 * @invar 	The radius of each ship must be a valid value.
 * 			|isValidRadius(getRadius())
 * 
 * @invar   The coordinates of a ship must be finit numbers.
 * 			|isValidPosition(getxPosition,getyPosition);
 *   
 * @version 1.0     
 * @author James Defauw & Michiel De Koninck
 
 */

public class Ship {

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
	 * @throws IllegalPositionException 
	 * @throws IllegalRadiusException 
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
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalPositionException, IllegalRadiusException{
		
		// At this point we can invoke our mutators. They will see to it that the class invariants hold at all times.
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);	
	}
	
	
	/**
	 * Initialize this new ship with their parameters (position,speed,radius,orientation) set to their lowest possible values.
	 * @throws IllegalPositionException 
	 * @throws IllegalRadiusException 
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
	 * i.e. :   |this(0.0,0.0,Min_Velocity,Max_Velocity,Min_Radius,Minimum_Orientation);
	 * 			
	 */
	public Ship() throws IllegalPositionException, IllegalRadiusException{
		this(0.0,0.0,Min_Velocity,Max_Velocity,Min_Radius,Minimum_Orientation);
	}
	 
	// -----------------------  VARIABLES (DEFAULTS & FINAL) ----------------------------------- 
	
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
	private double xVelocity = Min_Velocity;
	
	
	/**
	 * Variable registering the yVelocity of this Ship.
	 */
	private double yVelocity = Min_Velocity;
	
	
	/**
	 * Variable registering the minimum allowed velocity.
	 */
	private static final double Min_Velocity = 0;
	
	/**
	 * Variable registering the maximum allowed velocity.
	 */
	private static final double Max_Velocity = 300000;
	
	/**
	 * Variable registering the orientation of this Ship.
	 */
	private double orientation = Minimum_Orientation;
	
	/**
	 * Variable registering the minimum allowed orientation
	 */
	private static final double Minimum_Orientation = 0.0;
	
	
	/**
	 * Variable registering the maximum allowed orientation
	 */
	private static final double Maximum_Orientation = 2.0*Math.PI;
	
	
	/**
	 * Variable registering the radius of this Ship.
	 */
	private double radius = Min_Radius;

	/**
	 * Variable registering the minimum allowed Radius.
	 */
	private static final double Min_Radius = 10;


	
	

// ------------------------   The inspectors -------------------------------------------------------
	
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
		return Math.sqrt(Math.pow(getyVelocity(),2.0)+Math.pow(getxVelocity(),2.0));	
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
	
	/** 
	 *  Returns the x and y coordinates within an array.
	 *  
	 * @return the x and y coordinate as an array.
	 * 
	 */
	public double[] getPosition(){
		
		double[] position = {this.getxPosition(),this.getyPosition()};	
		return position;			
	}
	
	
// ------------------------------------ SETTERS --------------------------
	
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
	 * @throws IllegalPositonException
	 * 		 The given xPosition or yPosition is not valid.
	 * 	     | !isValidPosition(xPosition,yPosition)
	 */
	public void setPosition(double xPosition, double yPosition) 
			throws IllegalPositionException{
		if (!isValidPosition(xPosition,yPosition))
			throw new IllegalPositionException(xPosition,yPosition);
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
	 *@post If the new total velocity does not exceed the maximum velocity
	 *		and both components are postive then the given values are set as the new velocity.
	 *		|if (!new.exceedsMaxVelocity() & hasPositiveComponents())
	 *		|		then new.xVelocity = xVelocity;
	 *   				 new.yVelocity = yVelocity;
	 * 
	 * @post If the new total velocity exceeds the maximum velocity, 
	 * 		 both velocities scaled so that the class invariants hold.
	 * 		|if (new.exceedsMaxVelocity()) 
	 * 		|   then new.scaleVelocity()
	 * 
	 * @post If the given velocity has negative components, nothing is changed.
	 * 		|if !hasPositiveComponents(xVelocity, yVelocity)
	 * 		|    then new.getVelocity() = this.getVelocity()
	 * 
	 * @note The last postcondition is not really necessary.
	 * 		 Everything that is not changed within the method is left  untouched.
	 */
	public void setVelocity(double xVelocity, double yVelocity){
		
		
		if(this.hasPositiveComponents(xVelocity, yVelocity)){
			
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
			
			if(this.exceedsMaxVelocity(xVelocity, yVelocity)){
				this.scaleVelocity(xVelocity, yVelocity);
			}
		}
	} 
					
		
	
	/**
	 * Check whether the velocity has only positive components.
	 * @param xVelocity
	 * 		  The velocity in the x-direction
	 * @param yVelocity
	 * 		  The velocity in the y-direction
	 * 
	 * @return If either of the velocities is negative, the method returns false.
	 * 		   Otherwise it will return true.
	 * 		   |(xVelocity >=0) && (yVelocity >=0); 
	 */
	public boolean hasPositiveComponents(double xVelocity, double yVelocity){
		return (xVelocity >=0) && (yVelocity >=0);
	}
	
	/**
	 * Check whether the velocity exceeds the maximum allowed velocity.
	 * 
	 * @param xVelocity
	 * 		  The velocity in the x-direction
	 * @param yVelocity
	 *        The velocity in the y-direction
	 *        
	 * @return if the total velocity (which is computed
	 * 		  by taking the square root of the sum of the
	 * 		  secondpowers of the horizontal and vertical velocity.
	 * 		 -> sqrt(Vx^2+Vy^2)) exceeds the maximum. This returns true.
	 * 		 Otherwise it will return false.
	 * 		 | (Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2)) > Max_Velocity);	
	 */
	public boolean exceedsMaxVelocity(double xVelocity, double yVelocity){
		return (Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2)) > Max_Velocity);
		
	}
	
	/** Changes the velocity to it's scaled value (so that the class invariants hold)
	 *  .
	 * 
	 * @param xVelocity
	 * 		  The velocity in the x-direction
	 * @param yVelocity
	 *        The velocity in the y-direction
	 * 
	 * @note This method is only called upon if the total velocity of the given parameters exceed the maximum velocity.
	 * 		 
	 * @post The velocities are changed to their scaled values. 
	 * 		 The velocity no longer exceeds the limit.
	 * 		| new.xVelocity = (xVelocity*Max_Velocity)/this.getVelocity();
	 *      | new.yVelocity = (yVelocity*Max_Velocity)/this.getVelocity();	     
	 */
	public void scaleVelocity(double xVelocity, double yVelocity ){
		double scaledxVelocity = (xVelocity*Max_Velocity)/this.getVelocity();
		double scaledyVelocity = (yVelocity*Max_Velocity)/this.getVelocity();
		
		this.xVelocity = scaledxVelocity;
		this.yVelocity = scaledyVelocity;
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
	public void setRadius(double radius) throws IllegalRadiusException{
		if (!isValidRadius(radius))
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	}
	
	/** 
	 * 
	 * @param  radius
	 * 		   The radius of the ship.
	 * 
	 * @return True if the radius exceeds the minimal radius
	 * 		   false if the radius is less than the minimal_radius.
	 * 		   | radius >= Min_Radius;
	 */
	public static boolean isValidRadius(double radius){
		return radius >= Min_Radius;
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
     * 		  The orientation of which we need to check whether it is legal.
     * 
     * @return True if and only if the given orientation is within the boundaries opposed upon orientation.
     * 		   |result == (Minimum_Orientation <= orientation) && (orientation < Maximum_Orientation)
     * 
     */
    public static boolean isValidOrientation(double orientation){
    	return (Minimum_Orientation <= orientation) && (orientation <= Maximum_Orientation);	
    }
    	
    			
 
    
 //----------------- Moving, turning and accelerating----------------------------------------------------------------------
    
	/** Total Programming
	 * 
	 * @param acceleration
	 * 
	 * @post if the given acceleration is negative
	 * 		 The velocity is left untouched
	 * 		 |if acceleration < 0
	 * 		 | 		then new.getxVelocity() = getxVelocity()
	 * 	     | 		then new.getyVelocity() = getyVelocity()
	 * 
	 * @post if the given acceleration is greater than zero, the new velocities
	 * 		 are computed as follows:
	 * 		 |NewxVelocity = this.getxVelocity() + a*(Math.cos(this.getOrientation()));
		     |NewyVelocity = this.getyVelocity() + a*(Math.sin(this.getOrientation()));
		
	 * @effect The velocity is changed, dependent on the given acceleration.
	 * 	      This is only the case if the calculated new velocity is legal.
	 * 		  The method setVelocity makes sure that this is the case.
		
	 * 		  |this.setVelocity(NewxVelocity, NewyVelocity);
	 */
	public void thrust(double acceleration){
		//If the acceleration is negative, then we leave the velocity untouched.
		double a = Math.max(0, acceleration);
		double NewxVelocity = this.getxVelocity() + a*(Math.cos(this.getOrientation()));
		double NewyVelocity = this.getyVelocity() + a*(Math.sin(this.getOrientation()));
		
		this.setVelocity(NewxVelocity, NewyVelocity);	
	}
	
	/** Defensive programming
	 * 
	 * @param duration
	 * 		  The duration of the movement, given in seconds
	 * 
	 * @throws isValidDuration
	 * @throws IllegalPositionException 
	 * 
	 */
	public void move(double duration) throws IllegalPositionException{
		
		double newxPosition = this.getxPosition() + (duration)*(this.getxVelocity());
		double newyPosition = this.getyPosition() + (duration)*(this.getyVelocity());
		
		this.setPosition(newxPosition, newyPosition);
		
	}
	
	
	
	/** Nominal programming
	 * 
	 * 
	 * @param angle
	 * 
	 * @pre
	 */
	public void turn(double angle){
		double newAngle = this.getOrientation() + angle;
		this.setOrientation(newAngle);
	}

	
//  COLLISION PREDICTION  : DEFENSIVE
	
	public double getDistanceBetween(Ship ship){
		double centerDistance = Math.sqrt(Math.pow((this.getxPosition()-ship.xPosition), 2.0)+ Math.pow((this.getyPosition()-ship.yPosition), 2.0));
		return centerDistance - this.getRadius() - ship.radius;
	}
	
	public boolean overlap(Ship ship){
		return (this.getDistanceBetween(ship) < 0);	
	}
	
	
	/**
	 * 
	 * @param ship
	 * 
	 * @pre d > 0
	 * @pre DvDr > 0
	 * 
	 * @return Returns in how many seconds 2 spacecrafts will collide.
	 * 		   If they never collide it wil return Double.POSITIVE_INFINITY
	 * 
	 * @throws If two ships overlap, this method does not apply.
	 */
	public double getTimeToCollision(Ship ship){
		double sigma = Math.sqrt(Math.pow((this.getxPosition()-ship.xPosition), 2.0)+ Math.pow((this.getyPosition()-ship.yPosition), 2.0));
		double[] Dv= {ship.getxVelocity() - this.getxVelocity(), ship.getyVelocity() - this.getyVelocity()};
		double[] Dr= {ship.getxPosition() - this.getxPosition(), ship.getyPosition() - this.getyPosition()};
		
		// Avoided scalair multiplication by implementing this 'fake multiplication' to guarantee easy computing.
		double DrDr = Math.pow(Dr[0], 2.0)+Math.pow(Dr[1], 2.0);
		double DvDr = Dv[0]*Dr[0] + Dv[1]*Dr[1];
		double DvDv = Math.pow(Dv[0], 2.0)+Math.pow(Dv[1], 2.0);
		
		double d = Math.pow(DvDr, 2.0) - (DvDv)*(DrDr-Math.pow(sigma, 2.0));
		
		if ((d <= 0) || (DvDr >= 0)){
			return Double.POSITIVE_INFINITY;
		}
		
		else{
		return - (DvDr + Math.sqrt(d))/(DvDv);}
	}
	
	
	public double[] getCollisionPosition(Ship ship){
		
		//Using the time to collision, we now compute the position of the collision.
		//For this we first calculate where the two ships are at, at the time of collision.
		//Then we calculate where exactly they collide.
		
		double T = getTimeToCollision(ship);
		
		if (T == Double.POSITIVE_INFINITY){
			return null;
		}
		
		//Where are the ships after time T?
		
		double[] FirstShipPosition = {this.getxPosition() + this.getxVelocity()*T, this.getyPosition() + this.getyVelocity()*T};
		double[] SecondShipPosition = {ship.getxPosition() + ship.getxVelocity()*T, ship.getyPosition() + ship.getyVelocity()*T};
		
		//The position of the first ship, incremented with it's radius (in the right direction = direction to the center of the other ship) gives the answer
		
		double[] CenterDistance = {SecondShipPosition[0] - FirstShipPosition[0], SecondShipPosition[1]- FirstShipPosition[1]};
		
		double Norm = Math.sqrt(Math.pow(CenterDistance[0],2.0)+ Math.pow(CenterDistance[1],2.0));
		
		double[] NormedCenterDistance = {(SecondShipPosition[0] - FirstShipPosition[0])/Norm, (SecondShipPosition[1]- FirstShipPosition[1])/Norm};
		
		double[]RadiusWithDirection = {this.getRadius()*NormedCenterDistance[0],this.getRadius()*NormedCenterDistance[1]};
		
		double[] CollisionCoordinates = {FirstShipPosition[0]+RadiusWithDirection[0], FirstShipPosition[1]+RadiusWithDirection[1]};
		
		return CollisionCoordinates;
		
		}
		// TIP: Maak een klasse position aan met argumenten x en y zodat je er zeker van bent dat een position altijd 2ledig is.
		// In deze klasse zou je dan bv een methode kunnen maken voor de optelling van een position en een vector voor het maken van een nieuwe position.
		
	
}
