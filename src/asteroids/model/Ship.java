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
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation) 
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
		this(0.0,0.0,Min_Velocity,Min_Velocity,Min_Radius,Min_Orientation);
	}

	// -----------------------  VARIABLES (DEFAULTS & FINAL) --------

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
	private double orientation = Min_Orientation;

	/**
	 * Variable registering the minimum allowed orientation
	 */
	private static final double Min_Orientation = 0.0;


	/**
	 * Variable registering the maximum allowed orientation
	 */
	private static final double Max_Orientation = 2.0*Math.PI;


	/**
	 * Variable registering the radius of this Ship.
	 */
	private double radius = Min_Radius;

	/**
	 * Variable registering the minimum allowed Radius.
	 */
	private static final double Min_Radius = 10.0;





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
	 * Returns the velocity in an array: (Vx,Vy).
	 * 
	 * @return A set of doubles; the xVelocity and yVelocity.
	 * 		   |double[] Velocity = {getxVelocity(),getyVelocity()}
	 * 		   |return Velocity
	 */
	public double[] getVelocity(){
		double[] Velocity = {getxVelocity(),getyVelocity()};
		return Velocity;
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
	public double getTotalVelocity(){
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
	@Immutable
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

	/** 
	 * Sets the position to the given coordinates, if these make for a valid position.
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
		if (!isValidPosition(xPosition,yPosition)){
			
			throw new IllegalPositionException(xPosition,yPosition);}
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	/**
	 * Returns whether the given Position is valid.
	 * 
	 * @param xPostion
	 * 		  The x-coordinate for this ship.	
	 * @param yPosition
	 * 		  The y-coordinate for this ship.	 
	 * @return True if and only if neither of the coordinates is either infinity or NaN
	 *		  | result == (isValidCoordinate(xPosition) && isValidCoordinate(yPosition))
	 */
	public static boolean isValidPosition(double xPosition, double yPosition){
	
		return (isValidCoordinate(xPosition) && isValidCoordinate(yPosition));
	}
	
	/**
	 * Returns whether this coordinate is valid
	 * 
	 * @param coordinate
	 * 		   The coordinate that we wish to inspect
	 * 
	 * @return True if and only if this coordinate is not infinity or not a number.
	 * 		   |result == (!Double.isNaN(coordinate) && (coordinate != Double.POSITIVE_INFINITY) 
				&& (coordinate != Double.NEGATIVE_INFINITY));
	 */
	public static boolean isValidCoordinate(double coordinate){
		return (!Double.isNaN(coordinate) && (coordinate != Double.POSITIVE_INFINITY) 
				&& (coordinate != Double.NEGATIVE_INFINITY));
	}
	

	/** 
	 * This method sets the Velocity to the given value.  
	 * 
	 * @param xVelocity
	 * 		  The new velocity in the x-direction for this ship.
	 * @param yVelocity
	 * 		  The new velocity in the y-direction for this ship.
	 * 
	 * @post if one of the given velocities is not a number, the velocities are left untouched.
	 * 	     if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity)){
	 * 				then new.getxVelocity() = getxVelocity()
	 * 					 new.getyVelocity() = getyVelocity()
	 * 
	 * @post If the new total velocity is valid and does not exceed the maximum velocity
	 *		 and both components are postive then the given values are set as the new velocity.
	 *		|if (!new.exceedsMaxVelocity())
	 *		|		then new.xVelocity = xVelocity;
	 *   				 new.yVelocity = yVelocity;
	 * 
	 * @post If the new total velocity exceeds the maximum velocity, 
	 * 		 both velocities scaled so that the class invariants hold.
	 * 		|if (new.exceedsMaxVelocity()) 
	 * 		|   then new.scaleVelocity()
	 * 
	 */
	public void setVelocity(double xVelocity, double yVelocity){
		
		if ((!Double.isNaN(xVelocity)) && (!Double.isNaN(yVelocity))){
		
		
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;

				if(this.exceedsMaxVelocity(xVelocity, yVelocity)){
					this.scaleVelocity(xVelocity, yVelocity);
		}		}
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

	/** 
	 * Changes the velocity to it's scaled value (so that the class invariants hold)
	 *  .
	 * 
	 * @param xVelocity
	 * 		  The velocity in the x-direction
	 * @param yVelocity
	 *        The velocity in the y-direction
	 * 		 
	 * @post The velocities are changed to their scaled values. 
	 * 		 The velocity no longer exceeds the limit.
	 * 		| new.xVelocity = (xVelocity*Max_Velocity)/this.getTotalVelocity();
	 *      | new.yVelocity = (yVelocity*Max_Velocity)/this.getTotalVelocity();	    
	 *      
	 * @note This method is only called upon if the total velocity 
	 * 		 of the given parameters exceed the maximum velocity.
	 */
	public void scaleVelocity(double xVelocity, double yVelocity ){
		double scaledxVelocity = (xVelocity*Max_Velocity)/this.getTotalVelocity();
		double scaledyVelocity = (yVelocity*Max_Velocity)/this.getTotalVelocity();

		this.xVelocity = scaledxVelocity;
		this.yVelocity = scaledyVelocity;
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


	
// Je programeert hier nominaal, dan moeten mensen maar een valid orientation geven, is ze negatief en mag dat niet?
// Dat kan jou niets schelen, wij werken met ValidOrientations, dat staat in de pre-conditie, de gebruiker moet daaraan voldoen.

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
	public void setOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;	
	}

// OPLETTEN MET HET GEBRUIK VAN "Min_Orientation" in je documentatie. 
// dit is immers een private variable, wat betekent dat de gebruiker die niet kan zien. Misschien best een methode
// getMin_Orientation invoeren.
	/**
	 *  Check whether the given orientaton is a valid value.
	 * 
	 * @param orientations
	 * 		  The orientation of which we need to check whether it is legal.
	 * 
	 * @return True if and only if the given orientation is within the boundaries opposed upon orientation. (And has to be a number)
	 * 		   |result == (getMin_Orientation <= orientation) && (orientation < getMax_Orientation) && !Double.isNaN(orientation)
	 * 
	 */
	public static boolean isValidOrientation(double orientation){
		return ((Min_Orientation <= (orientation)) && (orientation <= Max_Orientation) && (!Double.isNaN(orientation)));	
	}



	//-------- Moving, turning and accelerating-----

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

// Max_orientation is niet beschikbaar voor gebruikers, dit is een private variable, pas dcumentatie aan
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


	//  COLLISION PREDICTION  : DEFENSIVE

	
// Fout gemaakt hier moet je ook nog rekening houden dat de gebruiker het schip zelf 2 maal kan opgeven.
// Hier moeten dus 2 return clauses staan.
	/**
	 *  Return the distance betwheen two ships.
	 * 
	 * @param other
	 *        The other ship of which we want to know the distance to this ship.
	 * 
	 * @return If the 2 ships are in fact different ship; The distance between the two ships. Computed as below:
	 * 		   | centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.xPosition), 2.0)+ 
	 * 											Math.pow((this.getyPosition()-other.yPosition), 2.0));
	 * 		   | result  == centerDistance - this.getRadius() - other.radius;
	 * 
	 * @return If the given ships refer the same ship, 0 will be the returned result.
	 * 		   |if ship == other ship
	 * 		   |result == 0
	 */
	public double getDistanceBetween(Ship other){
		double result = 0;
		if (this != other){
		double centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.xPosition), 2.0)+
									Math.pow((this.getyPosition()-other.yPosition), 2.0));
		result = centerDistance - this.getRadius() - other.radius;
		}
		return result;
	}


	/**
	 * Check whether two ships overlap.
	 * 
	 * @param other
	 * 		  The other ship of which we want to check if it overlaps with this ship.
	 * @return True if and only if the distance between the two ships is less than zero.
	 * 		   | getDistanceBetween(other) < 0
	 */
	public boolean overlap(Ship other){
		return (this.getDistanceBetween(other) < 0);	
	}

// DECLARATIEVE SPECIFICATIE:
	// HOUDT in dat je duidt op wat het nut is van de functie, wat je kan doen met het resultaat.
	//Je bespreekt dus niet exact hoe het resultaat bekomen wordt, maar wel wat je ermee kan doen.
	// Daar heeft de gebruiker immers het meeste aan.
	
	/**
	 *  Calculates the time to the point where the two given ships collide.
	 * 	If they never collide, it returns positive infinity.
	 * 
	 * @param other
	 * 		  The other ship with which this ship might collide
	 * 
	 * 
	 * @return Returns in how many seconds 2 entities will collide.
	 * 		   If they never collide it wil return positive infinity.
	 *			
	 *		   This result can, for example, be used to calculate the distance between a ship and
	 *		   the collision with the other object. This simply by multiplying the time (found by this method)
	 *		   with the speed of the entity. One could also use the method move to move the ship to to the place of collision.
	 * 
	 *
	 * @see implementation		   
	 *	   	
	 * @throws IllegalCollisionException
	 * 		   If two ships overlap, this method does not apply.
	 */
	public double getTimeToCollision(Ship other) throws IllegalCollisionException {
		if (overlap(other)){
			throw new IllegalCollisionException(this,other);
		}
		
		//Sigma is centerdistance at the moment of collision : sum of two radii.
		double sigma = other.getRadius() + this.getRadius();
		double[] Dv= {other.getxVelocity() - this.getxVelocity(), other.getyVelocity() - this.getyVelocity()};
		double[] Dr= {other.getxPosition() - this.getxPosition(), other.getyPosition() - this.getyPosition()};

		// Avoided scalair product by implementing this 'fake multiplication' to guarantee easy computing.
		double DrDr = Math.pow(Dr[0], 2.0)+Math.pow(Dr[1], 2.0);
		double DvDr = Dv[0]*Dr[0] + Dv[1]*Dr[1];
		double DvDv = Math.pow(Dv[0], 2.0)+Math.pow(Dv[1], 2.0);

		double d = Math.pow(DvDr, 2.0) - (DvDv)*(DrDr- Math.pow(sigma, 2.0));

		if ((d <= 0) || (DvDr >= 0)){
			return Double.POSITIVE_INFINITY;
		}
		
		else{
			return -(DvDr + Math.sqrt(d))/(DvDv);}
	}

	

	
	/** 
	 * Returns the position on which two ships collide, if they ever collide. Otherwise it returns null.
	 * 
	 * @param other
	 * 		  The other ship with which this ship might collide.
	 * 
	 * @return The Position (an array) on which two ships collide (if they ever collide).
	 * 		   Computation starts from initial positions and calculates the difference in positions at the time of collision.
	 * 		   This information is used to return the Collision coordinates.
	 * 		   Null if they never collide.
	 * 
	 * @see implementation
	 *		
	 * 
	 * @throws IllegalCollisionException
	 * 		   Created within getTimeToCollision(other)
	 * 		   We cannot calculate the collision position of two overlapping ships.
	 */
	public double[] getCollisionPosition(Ship other) throws IllegalCollisionException{

		//Using the time to collision, we now compute the position of the collision.
		//For this we first calculate where the two others are at, at the time of collision.
		//Then we calculate where exactly they collide. 

		double T = getTimeToCollision(other);

		if (T == Double.POSITIVE_INFINITY){
			return null;
		}

		//Where are the ships after time T?

		double[] FirstShipPosition = {this.getxPosition() + this.getxVelocity()*T, this.getyPosition() + this.getyVelocity()*T};
		double[] SecondShipPosition = {other.getxPosition() + other.getxVelocity()*T, other.getyPosition() + other.getyVelocity()*T};

		//The position of the first ship, incremented with it's radius 
		// (in the right direction = direction to the center of the other ship) results in the answer.

		double[] CenterDistance = {SecondShipPosition[0] - FirstShipPosition[0], SecondShipPosition[1]- FirstShipPosition[1]};
		double Norm = Math.sqrt(Math.pow(CenterDistance[0],2.0)+ Math.pow(CenterDistance[1],2.0));
		double[] NormedCenterDistance = {(SecondShipPosition[0] - FirstShipPosition[0])/Norm, (SecondShipPosition[1]- FirstShipPosition[1])/Norm};
		double[]RadiusWithDirection = {this.getRadius()*NormedCenterDistance[0],this.getRadius()*NormedCenterDistance[1]};
		double[] CollisionCoordinates = {FirstShipPosition[0]+RadiusWithDirection[0], FirstShipPosition[1]+RadiusWithDirection[1]};

		return CollisionCoordinates;
	}	
}
