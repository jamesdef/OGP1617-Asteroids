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
	 * @Override
	 */
	private static double Min_Radius = 10.0;
	
    /**
     * Variable registering the density of this ship.
     */
    private double density = Min_Density;
  
    /**
     * Variable registering the minimum allowed density.
     * @Override
     */
    
    private static final double Min_Density = 1.42*(Math.pow(10, 12));
    
    
    /**
     * Variable registering the Minimum allowed mass.
     */
    private final double Min_Mass = Min_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
    
    /**
     * Variable registering the mass of this ship.
     */
    private double mass = Min_Mass;
    
    
	// ------------------------   The inspectors -------------------------------------------------------



	// ------------------------------------ SETTERS --------------------------
 
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
